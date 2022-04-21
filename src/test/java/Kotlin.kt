import org.junit.Test

class Kotlin {
    var a = 3
    var b = 5

    fun sum(a : Int, b : Int) : Int {
        return a+b;
    }

    @Test
    fun main(){
        print(sum(5, 3))
    }


}