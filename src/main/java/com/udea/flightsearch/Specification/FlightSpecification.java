package com.udea.flightsearch.Specification;

import com.udea.flightsearch.model.Plane;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import com.udea.flightsearch.model.Flight;
import com.udea.flightsearch.model.Airport;
import com.udea.flightsearch.model.City;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightSpecification {

    public static Specification<Flight> filterBy(
            String originName,
            String destinationName,
            Integer passengerAmount,
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
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(originCityJoin.get("nameCity")), "%" + originName.toLowerCase() + "%"));
            }

            // Realiza lo mismo que el anterior pero para el nombre de destino
            if (destinationName != null && !destinationName.isEmpty()) {
                Join<Flight, Airport> destinationJoin = root.join("destination");   // Flight -> Airport (destination)
                Join<Airport, City> destinationCityJoin = destinationJoin.join("city"); // Airport -> City
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(destinationCityJoin.get("nameCity")), "%" + destinationName.toLowerCase() + "%"));
            }

            // Si el dia de salida es especificado, se agrega al query de busqueda
            if (departureDate != null) {
                Timestamp StartOfDay = Timestamp.valueOf(departureDate.atStartOfDay());
                Timestamp EndOfDay = Timestamp.valueOf(departureDate.plusDays(1).atStartOfDay().minusNanos(1));
                predicates.add(criteriaBuilder.between(root.get("departureDate"), StartOfDay, EndOfDay));
            }

            // Si el dia de llegada es especificado, se agrega al query de busqueda
            if (arrivalDate != null) {
                Timestamp StartOfDay = Timestamp.valueOf(arrivalDate.atStartOfDay());
                Timestamp EndOfDay = Timestamp.valueOf(arrivalDate.plusDays(1).atStartOfDay().minusNanos(1));
                predicates.add(criteriaBuilder.between(root.get("arrivalDate"), StartOfDay, EndOfDay));
            }

            // Filtra la busqueda entre un rango de precios
            if (minimumPrice != null && maximumPrice != null) {
                predicates.add(criteriaBuilder.between(root.get("price"), minimumPrice, maximumPrice));
            }

            // Filtra la busqueda entre un rango de Fechas (Ignorando la hora)
            if (minimumDate != null && maximumDate != null) {
                Timestamp minimumDateStartOfDay = Timestamp.valueOf(minimumDate.atStartOfDay());
                Timestamp maximumDateEndOfDay = Timestamp.valueOf(maximumDate.plusDays(1).atStartOfDay().minusNanos(1));
                predicates.add(criteriaBuilder.between(root.get("departureDate"), minimumDateStartOfDay, maximumDateEndOfDay));
            }

            // Filtra la busqueda entre un rango de horarios de salida (Ignorando Fecha)
            if (minimumTime != null && maximumTime != null) {
                Expression<Integer> departureHour = criteriaBuilder.function("DATE_PART", Integer.class, criteriaBuilder.literal("HOUR"), root.get("departureDate"));
                Expression<Integer> departureMinute = criteriaBuilder.function("DATE_PART", Integer.class, criteriaBuilder.literal("MINUTE"), root.get("departureDate"));

                Predicate timePredicate = criteriaBuilder.and(
                        criteriaBuilder.or(
                                criteriaBuilder.and(
                                        criteriaBuilder.equal(departureHour, minimumTime.getHour()),
                                        criteriaBuilder.greaterThanOrEqualTo(departureMinute, minimumTime.getMinute())
                                ),
                                criteriaBuilder.and(
                                        criteriaBuilder.equal(departureHour, maximumTime.getHour()),
                                        criteriaBuilder.lessThanOrEqualTo(departureMinute, maximumTime.getMinute())
                                ),
                                criteriaBuilder.and(
                                        criteriaBuilder.greaterThan(departureHour, minimumTime.getHour()),
                                        criteriaBuilder.lessThan(departureHour, maximumTime.getHour())
                                )
                        )
                );

                predicates.add(timePredicate);
            }

            //retorna solo vuelos que no estan cancelados
            predicates.add(criteriaBuilder.equal(root.get("isCanceled"), false));


            // Join with the 'Plane' entity
            Join<Flight, Plane> planeJoin = root.join("plane");

            // Calculate available seats: seatCapacity - soldSeats
            Expression<Integer> availableSeats = criteriaBuilder.diff(planeJoin.get("seatCapacity"), root.get("sellSeats"));

            // Add the predicate to check if availableSeats is greater than or equal to the specified minimum
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(availableSeats, passengerAmount));

            // Realiza ordenamiento, ya sea por dia de salida o por precio, este se realiza de manera ascendente
            if (orderByDepartureDateAsc) {
                query.orderBy(criteriaBuilder.asc(root.get("departureDate")));
            } else if (orderByPriceAsc) {
                query.orderBy(criteriaBuilder.asc(root.get("price")));
            }

            // Combine all predicates with 'AND'
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

