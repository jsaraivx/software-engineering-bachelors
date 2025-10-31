package com.jsaraivx.gerenciabanco; 

import java.util.Scanner;

/**
 * CLASSE CONTA:
 * Contém os atributos (dados pessoais e saldo) e os métodos para as operações bancárias.
 * Implementa o conceito de Orientação a Objetos.
 */
class Conta {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    public Conta(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0; // Saldo inicializa em zero
    }

    // Métodos (Operações Bancárias)

    /**
     * Realiza um depósito na conta.
     * @param valor O valor a ser depositado.
     */
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("\n*** Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso! ***");
        } else {
            System.out.println("\n!!! Valor de depósito inválido. Deve ser maior que zero. !!!");
        }
    }

    /**
     * Realiza um saque na conta.
     * @param valor O valor a ser sacado.
     * @return true se o saque for realizado, false caso contrário.
     */
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("\n*** Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso! ***");
            return true;
        } else if (valor > this.saldo) {
            System.out.println("\n!!! Saldo insuficiente para realizar o saque de R$ " + String.format("%.2f", valor) + ". !!!");
            return false;
        } else {
            System.out.println("\n!!! Valor de saque inválido. Deve ser maior que zero. !!!");
            return false;
        }
    }

    /**
     * Exibe o saldo atual da conta.
     */
    public void consultarSaldo() {
        System.out.println("\n=============================================");
        System.out.println("  CLIENTE: " + this.nome + " " + this.sobrenome);
        System.out.println("  CPF: " + this.cpf);
        System.out.println("---------------------------------------------");
        System.out.println("  SALDO ATUAL: R$ " + String.format("%.2f", this.saldo));
        System.out.println("=============================================");
    }
}

/**
 * CLASSE PRINCIPAL:
 * Contém o método main e a lógica do menu de interação com o usuário.
 * Utiliza o loop do...while e a estrutura switch...case.
 */
public class GerenciaBanco {

    private static void exibirMenu() {
        System.out.println("\n\n=============== MENU DE OPERAÇÕES ===============");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Realizar Depósito");
        System.out.println("3 - Realizar Saque");
        System.out.println("4 - Encerrar Aplicação");
        System.out.println("=================================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. INFORMAÇÕES DO USUÁRIO
        System.out.println("--- BEM-VINDO(A) AO GERENCIADOR BANCÁRIO ---");
        System.out.print("Informe seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Informe seu sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Informe seu CPF: ");
        String cpf = scanner.nextLine();
        
        // 2. INSTANCIAÇÃO DA CLASSE CONTA (Criação do Objeto)
        Conta contaCliente = new Conta(nome, sobrenome, cpf);

        int opcao;

        // 3. ESTRUTURA DE REPETIÇÃO (do...while)
        do {
            exibirMenu();
            // Tratamento de exceção simples para garantir que a entrada seja um número
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("\n!!! Entrada inválida. Por favor, digite um número de 1 a 4. !!!");
                scanner.next(); // Consome a entrada inválida
                opcao = 0;
                continue; // Volta para o início do loop
            }
            
            // 4. ESTRUTURA DE DECISÃO (switch...case)
            switch (opcao) {
                case 1:
                    // Chama o método da instância (objeto)
                    contaCliente.consultarSaldo();
                    break;
                case 2:
                    System.out.print("\nInforme o valor para depósito: R$ ");
                    if (scanner.hasNextDouble()) {
                        double valorDeposito = scanner.nextDouble();
                        contaCliente.depositar(valorDeposito);
                    } else {
                         System.out.println("\n!!! Valor inválido. Digite um número decimal ou inteiro. !!!");
                         scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("\nInforme o valor para saque: R$ ");
                     if (scanner.hasNextDouble()) {
                        double valorSaque = scanner.nextDouble();
                        contaCliente.sacar(valorSaque);
                    } else {
                         System.out.println("\n!!! Valor inválido. Digite um número decimal ou inteiro. !!!");
                         scanner.next();
                    }
                    break;
                case 4:
                    System.out.println("\n*** Obrigado por utilizar o Gerenciador Bancário. Aplicação encerrada. ***");
                    break;
                default:
                    System.out.println("\n!!! Opção inválida. Tente novamente. !!!");
                    break;
            }
        } while (opcao != 4); // Condição de saída
        
        scanner.close();
    }
}