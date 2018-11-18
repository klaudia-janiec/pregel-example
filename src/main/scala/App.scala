import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.{SparkConf, SparkContext}

object App {
  def main(arguments: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("Plggdes")
    val sc = new SparkContext(sparkConf)

    val islands = sc.parallelize(
      Array(
        (1L, "1"),
        (2L, "2"),
        (3L, "3"),
        (4L, "4"),
        (5L, "5"),
        (6L, "6"),
        (7L, "7"),
        (8L, "8"),
        (9L, "9"),
        (10L, "10")
      )
    )

    val edges = sc.parallelize(
      Array(
        Edge(1L, 2L, 1),
        Edge(2L, 3L, 2),
        Edge(3L, 4L, 3),
        Edge(4L, 5L, 4),
        Edge(5L, 6L, 5),
        Edge(6L, 7L, 6),
        Edge(7L, 8L, 7),
        Edge(8L, 9L, 8),
        Edge(9L, 10L, 9),
        Edge(10L, 1L, 10)
      )
    )

    val graph = Graph(islands, edges, "0")

    graph.pregel(1, 400)(
      (_, dist, _) => dist,
      triplet => Iterator((triplet.dstId, 1)),
      (a, b) => math.min(a, b)
    )
  }
}
