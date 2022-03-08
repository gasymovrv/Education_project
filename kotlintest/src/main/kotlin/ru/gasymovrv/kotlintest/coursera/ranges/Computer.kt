package ru.gasymovrv.kotlintest.coursera.ranges

class Computer(
  var hdd: Int,
  private val cpuCores: Int,
  private val cpuFrequency: Int,//MHz
  private val ram: Int,//Gb
  private val rom: Int//Gb
) : Comparable<Computer> {

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
    var result = hdd - other.hdd
    result += (cpuCores - other.cpuCores) * 100
    result += cpuFrequency - other.cpuFrequency
    result += (ram - other.ram) * 100
    result += rom - other.rom
    return result
  }

  override fun toString(): String {
    return "Computer(hdd=$hdd, cpuCores=$cpuCores, cpuFrequency=$cpuFrequency, ram=$ram, rom=$rom)"
  }

}