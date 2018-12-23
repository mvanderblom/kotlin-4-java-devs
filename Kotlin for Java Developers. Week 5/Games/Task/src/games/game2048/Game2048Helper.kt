package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val result = mutableListOf<T>()
    val nonEmptyCells = this
            .filterNotNull();

    var i = 0
    while(i < nonEmptyCells.size) {
        val curVal = nonEmptyCells[i]
        if(nonEmptyCells.size > i + 1 && curVal == nonEmptyCells[i+1]) {
            result.add(merge(curVal))
            i++
        } else {
            result.add(curVal);
        }
        i++
    }
//
//    for( i in 0 until nonEmptyCells.size) {
//        val curVal = nonEmptyCells[i]
//        if(nonEmptyCells.size > i + 1) {
//            val nextVal = nonEmptyCells[i+1];
//            if(curVal == nextVal){
//                result.add(merge(curVal))
//                println('a')
//            }
//            else {
//                result.add(curVal);
//                println('b')
//            }
//        }else {
//            result.add(curVal);
//            println('c')
//        }
//    }
//
//    nonEmptyCells
//            .forEachIndexed { i, t ->
//                if(i > 0) {
//                    if (this[i - 1] == t)
//                        result.add(merge(t))
//                    else
//                        result.add(t)
//                }
//            }
    return result

//    return nonEmptyCells
//            .mapIndexed { i, t ->
//        if(i == 0)
//            t
//        else if(t == nonEmptyCells[i-1])
//            nonEmptyCells.removeAt(i);
//            merge(t)
//        else
//            t
//
//    }
//    if( nonEmptyCells.size > 1) {
//        with(nonEmptyCells) {
//            for (i in 1 until this.size) {
//                if (this[i] == this[i - 1]) {
//                    this[i - 1] = merge(this[i])
//                    this.removeAt(i)
//                }
//            }
//        }
//    }
//    return nonEmptyCells;
}
fun <T : Any> List<T?>.moveAndMergeEqual2(merge: (T) -> T): List<T> = this
        .mapIndexed{i, t -> if(this.size > i+1) t to this[i+1] else t to null }
        .filter { p -> p.first != null}
        .map { p -> if(p.first == p.second) merge(p.first!!) else p.first!!}

fun <T: Any> nextEquals(l: List<T?>, i: Int): Boolean = l.size > i+1 && l.get(i) == l.get(i+1)