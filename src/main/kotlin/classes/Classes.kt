/**
 * The simplest class
 * Primary constructor
 * Property initializers
 * Init blocks
 * Secondary constructors
 * Default constructor
 * Constructor with default parameter values
 * Implicit default primary constructor call
 *   When there is no primary constructor - default primary constructor gets generated and will be called implicitly
 *   Init block codes if present will get executed in the primary constructor 
 * How and when the properties in the class body gets initialized
 * How and when the code in init blocks get executed
 */


/**
* A class can be as simple as just the name of the class added next to the 'class' keyword
* Create instances of this class
* Assign to variables, pass around, compare instances
* All classes inherit from class Any. Can invoke public functions from Any on an instance of Simplest class
*/
class Simplest

/**
* Class with a primary constructor and a function
* Using val on a constructor parameter makes it property of the class
* It would have a backing field with a getter. A var parameter would have a setter as well
* Note the use of also function call, to printout debugging information to console
* What does this function do? where is it defined? How are we able to invoke it on an Int object?
*   - It invokes the function or lambda passed in, passing it one parameter this and returns the same this reference
*   - It is an extension function defined on generic type T, which makes it to be invoked from any object instance
*/
class Box(val side : Int) {
    fun volume() = (side * side * side).also(::println)
}

/**
* Class with a primary constructor, with constructor keyword explicitly mentioned
* Also it has a property initializer to initialize area, based on the property set in constructor
* The property gets initialized only once, when the object instance is created
* Unlike the volume function in the previous example where volume gets calculated on each call.
*/
class Square constructor (val side : Int) {
    val area = (side * side).also(::println)
}

/**
* Class with a primary constructor and initialization blocks
* Property set in primary constructor can be accessed in initialization blocks
* Effectively, the code in init blocks gets executed at the end of primary constructor. 
* Code in init blocks becomes part of primary constructor during compilation
*/
class SquareWithMargin(var side : Int) {

    init {
        println("Adding a margin to the sides")
        side += 2 
        println("Full size of the side is $side")
    }
    fun area() = (side * side).also(::println)
}

/**
* A class with no primary constructor. Has only a secondary constructor
* Properties declared in the class body
*/
class Rectangle {

    val width : Int
    val length : Int

    constructor(_length: Int, width : Int) {
        length = _length // This is the Kotlin naming convention
        this.width = width // Can use Java convention as well
    }

}

/**
* What happens if we have init blocks in the Rectangle class above?
* When would they get executed?
*/
class IRectangle {

    val width : Int
    val length : Int

    constructor(_length: Int, _width : Int) {
        length = _length
        this.width = _width
        println("In the secondary constructor... Length : $length")
    }

    init {
        
        // This gives compilation error... variable 'length' must be initialized
        // The rule - variables must be initialized before its used enfored by Kotlin compiler
        // Effectively hides initializing values to default values that happens in Java
        /*
        println("In the initialization block... Length : $length")
        */

        // This gets executed before secondary construtor is called.
        println("In the initialization block...")
    }
}

/**
* What if there are multiple initialization blocks...
* What is the order of their execution?
* Init blocks gets executed in the order in which its defined
* All init blocks are executed before secondary constructor 
* Property initialization is also part of init block codes and happens in the same order
*/
class MIRectangle {

    val width : Int
    val length : Int

    init {
        println("Init block 1")

        // Cannot access here... compilation error
        /*
        println("Margin : $margin")
        */
    }

    val margin = 1

    init {
        println("Init block 2")

        // But can be accessed here
        println("Margin : $margin")
    }


    constructor(_length: Int, _width : Int) {
        length = _length
        this.width = _width
        println("In the secondary constructor... Length : $length")
    }

    init {
        // This gets executed before the secondary constructor
        println("Init block 3")
    }
}


/**
* Property declaration should be done before it is initialized in an init block
* Property declared inside an init block is not visible outside of it... it behaves like a regular variable, with init block as its scope
*/
class InitRectangle {

    val width : Int
    val length : Int

    init {
        println("Init block 1")
        width = 10
        length = 20

        // Compilation error. Declaration of variable has to be before initialization
        /*
        margin = 1
        */

        val thickness = 0.5

    }

    val margin : Int

    init {

        // Not allowed
        /*
        thickness = 0.25
        */

        // Allowed
        margin = 1

    }
}

/**
* With default values for primary constructor properties
* Behaves as having multiple primary constructors
* Secondary constructor explicitly calls the primary constructor option to use
*/
class DefaultRectangle (val length : Int = 10, val breadth : Int = 20) {
    init{
        println("Rectangle initialized to ($length X $breadth)")
    }

    constructor(colored : Boolean) : this() {
        println("With boolean constructor")
    }
}


/**
* With annotation to generate overloaded constructors
*/
class DefaultOverloadedRectangle @JvmOverloads  constructor (val length : Int = 10, val breadth : Int = 20) {
    init{
        println("Rectangle initialized to ($length X $breadth)")
    }

    constructor(colored : Boolean) : this() {
        println("With boolean constructor")
    }
}


/**
* Main function to test the examples
*/
fun main(args : Array<String>) {
 
    val obj1 = Simplest()
    val obj2 = Simplest()

    val obj3 = obj1

    println("Comparing object instances (obj1 == obj2) : ${obj1 == obj2}")
    println("Comparing object instances (obj3 == obj1) : ${obj3 == obj1}")

    val box = Box(10)
    println("Volume of box (box.volume()) : ${box.volume()}")

    val sq = Square(10)
    println("Area of square (sq.area) : ${sq.area}")

    val msquare = SquareWithMargin(10)
    println("Area of msquare (msquare.area()) : ${msquare.area()}")

    Rectangle(10, 20)
    IRectangle(21, 43)
    MIRectangle(88, 99)
    InitRectangle()


    DefaultRectangle()
    DefaultRectangle(33)
    DefaultRectangle(55, 5)
    DefaultRectangle(false)

    DefaultOverloadedRectangle()
    DefaultOverloadedRectangle(100)
    DefaultOverloadedRectangle(100, 20)
    DefaultOverloadedRectangle(true)

}