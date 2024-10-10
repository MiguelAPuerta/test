package com.udea.flightsearch.Specification;

import jakarta.persistence.criteria.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.model.Airport;
import com.udea.flightsearch.model.City;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightSpecification {

    public static Specification<Flight> filterBy(
            String originName,
            String destinationName,
            LocalDate departureDate,
            LocalDate arrivalDate,
            Double minimumPrice,
            Double maximumPrice,
            LocalDate minimumDate,
            LocalDate maximumDate,
            LocalTime minimumTime,
            LocalTime maximumTime,
            boolean orderByDepartureDateAsc,
            boolean orderByPriceAsc
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Comprueba que exista un nombre de origen, realiza el join entre Flight y Airport, tambien el join entre Airport y City
            // Finalmente crea una criteria para buscar por el nombre de origen sin importar si posee mayusculas o no.
            if (originName != null && !originName.isEmpty()) {
                Join<Flight, Airport> originJoin = root.join("origin");   // Flight -> Airport (origin)
                Join<Airport, City> originCityJoin = originJoin.join("city"); // Airport -> City
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(originCityJoin.get("name")), "%" + originName.toLowerCase() + "%"));
            }

            // Realiza lo mismo que el anterior pero para el nombre de destino
            if (destinationName != null && !destinationName.isEmpty()) {
                Join<Flight, Airport> destinationJoin = root.join("destination");   // Flight -> Airport (destination)
                Join<Airport, City> destinationCityJoin = destinationJoin.join("city"); // Airport -> City
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(destinationCityJoin.get("name")), "%" + destinationName.toLowerCase() + "%"));
            }

            // Si el dia de salida es especificado, se agrega al query de busqueda
            if (departureDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("departureDate"), departureDate));
            }

            // Si el dia de llegada es especificado, se agrega al query de busqueda
            if (arrivalDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("arrivalDate"), arrivalDate));
            }

            // Filtra la busqueda entre un rango de precios
            if (minimumPrice != null && maximumPrice != null) {
                predicates.add(criteriaBuilder.between(root.get("price"), minimumPrice, maximumPrice));
            }

            // Filtra la busqueda entre un rango de Fechas
            if (minimumDate != null && maximumDate != null) {
                predicates.add(criteriaBuilder.between(root.get("departureDate"), minimumDate, maximumDate));
            }

            // Filtra la busqueda entre un rango de horarios de salida
            if (minimumTime != null && maximumTime != null) {
                predicates.add(criteriaBuilder.between(root.get("departureTime"), minimumTime, maximumTime));
            }

            //retorna solo vuelos que no estan cancelados
            predicates.add(criteriaBuilder.equal(root.get("isCanceled"), false));

            // Realiza ordenamiento, ya sea por dia de salida o por precio, este se realiza de manera ascendente
            if (orderByDepartureDateAsc) {
                query.orderBy(criteriaBuilder.asc(root.get("departureDate")), criteriaBuilder.asc(root.get("departureTime")));
            } else if (orderByPriceAsc) {
                query.orderBy(criteriaBuilder.asc(root.get("price")));
            }

            // Combine all predicates with 'AND'
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

