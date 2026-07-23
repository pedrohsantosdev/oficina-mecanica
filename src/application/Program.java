package application;

import db.DB;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.statusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {

    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Cliente cliente = new Cliente(1, "Pedro", "12345678911", "(33)99999-9988",
                "pedro@gmail.com");

        System.out.println(cliente);

        System.out.println();

        Veiculo veiculo = new Veiculo(1, cliente, "BBR1B23", "Toyota", "Hilux SRX", 2024,
                "Branco", 100000);

        System.out.println(veiculo);

        System.out.println();

        OrdemServico ordem = new OrdemServico(1, veiculo, LocalDate.parse("20/07/2026", dtf), LocalDate.parse("22/07/2026", dtf),
                "Motor falhando", "Manutenção no motor", 500.00, statusOrdem.EM_ANDAMENTO);

        System.out.println(ordem);
    }
}
