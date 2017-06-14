package com.dannis.testkotlin.syntax

/**
 * Created by acadsoc on 2017-06-14.
 * Reference From :
 * http://blog.csdn.net/u010361662/article/details/54022209

classModifier: 类属性修饰符，标示类本身特性。
abstract    //抽象类标示
final       //标示类不可继承，默认属性
enum        //标示类为枚举
open        //类可继承，类默认是final的
annotation  //注解类

accessModifier: 访问权限修饰符
private //仅在同一个文件中可见
protected //同一个文件中或子类可见
public //所有调用的地方都可见
internal //同一个模块中可见

 */

// 有var则生成get,set
//class User constructor(var name: String)

// 当constructor前无修饰符(如：private)时，constructor可以省略：
//class User internal constructor (var name: String)
class User(var name: String)

data class SubUser(var name: String) {
    var age: Int = 0

    init {
        this.age = 123
    }

    /**secondary constructor**/
    constructor(name: String, age: Int) : this(name) {
        //初始化...
        this.age = age
    }

}

open class Man {
    open fun study() {
        println("man study")
    }
}

interface IReadable {
    fun study() {
        println("IRead study")
    }
}

class Stu : Man(), IReadable {
    override fun study() {
        super<Man>.study()
        super<IReadable>.study()
        println("Stu study")
    }
}


fun main(args: Array<String>) {
    var u = User("user-1")
    println(u.name)

    var su = SubUser("subuser-1", 30)
    println(su)
    println(su.age)

    var su2 = SubUser("subuser-2")
    var su3 = SubUser("subuser-2")
    println(su2)
    println(su2.age)
    println(su2 == su3)     // 类声明data即为true,自动生成equal,hashCode,toString,

    var s = Stu()
    s.study()
}

