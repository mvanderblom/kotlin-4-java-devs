package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean =
        permutation.mapIndexed{ i, p_outer->
            permutation
                    .filterIndexed { j,p_inner-> i < j && p_outer > p_inner}
                    .count()
        }.sum() % 2 == 0

//    you have an outer and an inner loop (maybe you use map for that),
//    both going over all the indices 0 until size(P) of your permutation P
//    (which is your list); call them e.g. i and j
//
//    in the inner loop, filter all elements i < j
//
//    then, in the inner loop, filter P[i] > P[j] (these are called inverted pairs)
//
//    count them in the inner loop
//
//    sum these counts in the outer loop
//    The sum is a number that's either even or odd. This is the parity algorithm as
//    described in the task description.