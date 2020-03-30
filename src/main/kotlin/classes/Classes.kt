/**
 * A simple class
 * Primary constructor
 * Property initializers
 * Init blocks
 * Secondary constructors
 * Default constructor
 * Constructor with default parameter values
 * Implicit default constructor call
 * How and when the code in init blocks get executed
 *   At the end of primary constructor
 * How and when the properties in the class body gets initialized
 */

/**
* A class can be as simple as just the name of the class added next to the 'class' keyword
*/
class SimpleClass

/*
* Class with a primary constructor
*/
class Box(val side : Int) {
    fun volume() = (side * side * side).also(::println)
}

/*
* Class with a primary constructor, (optoinal) constructor keyword explicitly mentioned
* Also has property initializer to initialize area, based on the property set in constructor
*/
class Square constructor (val side : Int) {
    val area = (side * side).also(::println)
}

/*
* Class with a primary constructor and initialization blocks
* Property set in primary constructor can be accessed in initialization blocks
*/
class IBox(var side : Int) {

    init {
        println("Adding a margin to 2 to the side")
        side += 2 
        println("Now the side is $side")
    }
    fun volume() = (side * side * side).also(::println)
}

/*
* Class with only secondary constructor
*/
class Rectangle {

    val width : Int
    val length = 22

    val newFun : () -> Unit

    fun above() = println("I am above all")

    init {
        val color = "Blue"
        println("Init block in rectangle ${length}")
        println("Color is $color")
        above()
        below()
    }

    fun below() = println("I am below init")

    

    constructor(length: Int, width : Int) {
        println("Constructor of rectangle.. with length $length")
        //this.length = length
        this.width = width
        //println("Color is $color")
    }

    init {
        println("Last Init block in rectangle ${length}")
        newFun = {println("Its really fun")}
        newFun()

    }

}

class MyShape @JvmOverloads constructor (val side : Int = 10, val thickness : Int = 2) {
    init{
        println("MyShape initialized to $side X $thickness")
    }

    constructor(colored : Boolean) : this() {
        println("With boolean constructor")
    }
}



fun main(args : Array<String>) {
    println("A simple class")

    println("Creating an instance of SimpleClass...")
    val obj = SimpleClass()

    println("Printing the object obj : $obj")

    println("Creating multiple instances of SimpleClass...")
    val obj1 = SimpleClass()
    val obj2 = SimpleClass()

    println("Assigning instances to variables...")
    val obj3 = obj1
    var obj4 = obj2

    println("Comparing object instances (obj1 == obj2) : ${obj1 == obj2}")
    println("Comparing object instances (obj3 == obj1) : ${obj3 == obj1}")
    println("Comparing object instances (obj4 == obj2) : ${obj4 == obj2}")

    val sq = Square(10)
    println("Area of square (sq.area) : ${sq.area}")
    println("Area of square (sq.area) : ${sq.area}")

    val box = Box(10)
    println("Volume of box (box.volume()) : ${box.volume()}")
    println("Volume of box (box.volume()) : ${box.volume()}")

    val ibox = IBox(10)
    println("Volume of ibox (ibox.volume()) : ${ibox.volume()}")

    val rect = Rectangle(10, 20)
    println("Length of rectangle (rect.length) : ${rect.length}")

    val myshape = MyShape()
    val myshape1 = MyShape(33)

    val myshape2 = MyShape(55, 5)

    val myshape3 = MyShape(true)

}