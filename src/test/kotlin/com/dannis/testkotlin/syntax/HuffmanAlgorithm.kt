package com.dannis.testkotlin.syntax

/**
 * Huffman algorithm
 * http://blog.csdn.net/qyee16/article/details/6664377
 */
//class HuffmanNode {
//    var weight: Double = 0.0
//    var parent:HuffmanNode? = null
//    var lchild:HuffmanNode? = null
//    var rchild:HuffmanNode? = null


// primary constructor 当前面无修饰符时，可以省略.
data class HuffmanNode /* constructor */(
        var weight: Double = 0.0,
        var parent: HuffmanNode? = null,
        var lchild: HuffmanNode? = null,
        var rchild: HuffmanNode? = null
) {

    // secondary constructor
    constructor(weight: Double, lchild: HuffmanNode?, rchild: HuffmanNode?)
            : this(weight, null, lchild, rchild)  /* {} */

    override fun toString(): String {
        var result = "$weight"
        if (lchild == null && rchild == null)
            return result
        else {
            result += " ( "
            if (lchild != null) result += "$lchild"
            result += " , "
            if (rchild != null) result += "$rchild"
            result += " ) "
            return result
        }
//        return "$weight ${if(lchild == null && rchild == null) "" else "()"}"
    }
}


fun main(args: Array<String>) {
    /*
    var l = HuffmanNode(weight = 2.0)
//    l.weight = 1234.0
//    l.lchild = null
//    l.rchild = null

    var r = HuffmanNode(3.0, null, null)

    var root = HuffmanNode(1.0, l, r)
//    root.weight = 123.0
//    root.lchild = null
//    root.rchild = l
    println(root)
    */
//    println(if (1 == 2) "yes" else "no")

    var nodes = mutableListOf(HuffmanNode(100.0), HuffmanNode(1.0), HuffmanNode(20.0), HuffmanNode(40.0))
    var root: HuffmanNode? = constructHuffmanTree(nodes)
    println(root)
    println("Done")
}

fun constructHuffmanTree(nodes: MutableList<HuffmanNode>): HuffmanNode? {
    if (nodes == null) return null
    if (nodes.count() == 1) return nodes[0]
    while (nodes.count() > 1) {
        var m1 = nodes.minBy { e -> e.weight }
        nodes.remove(m1)
        var m2 = nodes.minBy { e -> e.weight }
        nodes.remove(m2)
        var p = HuffmanNode(m1!!.weight + m2!!.weight, m1, m2)
        m1.parent = p
        m2.parent = p
        nodes.add(p)
    }
    return nodes[0]
}

