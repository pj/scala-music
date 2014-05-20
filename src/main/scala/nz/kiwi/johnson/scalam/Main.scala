package nz.kiwi.johnson.scalam

import scala.collection.JavaConversions._
import javax.sound.midi._
import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider

object Main {

  def main(args: Array[String]): Unit = {
    println("asdfasdfsadf")
    val serviceLoaders = ServiceLoader.load(classOf[MidiDeviceProvider]);
    
    println(serviceLoaders)
    
    val iterator = serviceLoaders.iterator
    
    while (iterator.hasNext) {
      println(iterator.next)
    }
    
    serviceLoaders.iterator map {
      deviceProvider => 
        println(deviceProvider)
        val deviceInfos = deviceProvider.getDeviceInfo()
        
        deviceInfos map {
          deviceInfo => 
            println(deviceInfo)
        }
    }
  }

}