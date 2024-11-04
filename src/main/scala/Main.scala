import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    println("Bem-vindo ao Scala Parallel Factorial Calculator!")

    // Definindo alguns números para calcular o fatorial
    val numbers = List(5, 10, 15)

    // Calcula o fatorial de cada número de forma assíncrona usando Future
    val futures = numbers.map { num =>
      Future {
        val result = factorial(num)
        println(s"Fatorial de $num é $result")
      }
    }

    // Aguardar a conclusão de todos os Futures
    Future.sequence(futures).onComplete {
      case Success(_) => println("Cálculos concluídos com sucesso!")
      case Failure(e) => println(s"Ocorreu um erro: $e")
    }
  }

  // Função para calcular o fatorial de forma recursiva
  def factorial(n: Int): BigInt = {
    if (n <= 1) 1 else n * factorial(n - 1)
  }
}
