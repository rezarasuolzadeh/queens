package ir.rezarasoulzadeh.queens.evaluate

class QueensLocations(queensLocationsString: ArrayList<String>, queensCount: Int) {

    private val queensLocations = ArrayList<Int>()

    init {
        for (i in queensLocationsString.indices) {
            val section = queensLocationsString[i].split(" ")
            val queenLocation = (section[0].toInt() * queensCount) - (section[1].toInt() % queensCount)
            queensLocations.add(queenLocation - 1)
        }
    }

    fun getQueensLocations(): ArrayList<Int> {
        return queensLocations
    }

}