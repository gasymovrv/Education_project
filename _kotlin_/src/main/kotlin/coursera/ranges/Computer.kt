package coursera.ranges

data class Computer(
    var rom: Int,
    private val cpuCores: Int,
    private val cpuFrequency: Int,//MHz
    private val ram: Int,//Gb
) : Comparable<Computer>, Iterable<Computer> {
//  var hdd: Int = 0 //Gb
//    set(value) {
//      if (value > 0) {
//        field = value
//      } else {
//        field = hdd
//        throw IllegalArgumentException("Incorrect value for HDD")
//      }
//    }

    override fun compareTo(other: Computer): Int {
        var result = rom - other.rom
        result += (cpuCores - other.cpuCores) * 100
        result += cpuFrequency - other.cpuFrequency
        result += (ram - other.ram) * 100
        return result
    }

    override fun toString(): String {
        return "Computer(hdd=$rom, cpuCores=$cpuCores, cpuFrequency=$cpuFrequency, ram=$ram)"
    }


    override fun iterator(): Iterator<Computer> {
        var i = 0;
        return object : Iterator<Computer> {
            override fun hasNext(): Boolean {
                return i++ < 5
            }

            override fun next(): Computer {
                return Computer(rom = 500, cpuCores = 8, cpuFrequency = 2400, ram = 8)
            }
        }
    }

}
