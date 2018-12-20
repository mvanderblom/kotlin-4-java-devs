package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        this.allDrivers.filter { driver ->
            this.trips.none { trip -> trip.driver == driver }
        }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers.filter { passenger ->
            this.trips.count { trip -> trip.passengers.contains(passenger) } >= minTrips
        }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.allPassengers.filter { passenger ->
            this.trips.count { trip ->
                trip.passengers.contains(passenger) && trip.driver == driver
            } > 1
        }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.trips.flatMap { trip ->
            (trip.passengers.map { passenger ->
                passenger to trip
            })
        }
                .groupBy({ (passenger, _) -> passenger },
                        { (_, trip) -> trip })

                .entries
                .filter { (_, trips) ->
                    trips
                            .count { trip -> trip.discount != null } > (trips.size / 2)
                }
                .map { (passenger, _) -> passenger }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return 0..9
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    true
}