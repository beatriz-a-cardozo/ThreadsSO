import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ================================ DECLARAÇÃO DE VARIÁVEIS =================================
        Scanner entrada = new Scanner(System.in);
        Hospital hospital =  null;

        String opc; // opção de scheduelling/qual hospital usar
        boolean flag = true; // flag para a sincronização
        boolean input = true; // variável auxiliar para a verificação de input
        char sin; // entrada de dados para a sincronia
        int num_medicos; // numero de medicos/threads

        // ==================================== ENTRADA DE DADOS ====================================
        System.out.println("[INPUT] Quantos MEDICOS deseja simular?");
        num_medicos = entrada.nextInt();

        do{
            System.out.println();
            System.out.println("[INPUT] Qual tipo de HOSPITAL(SCHEDUELLING) deseja simular?");
            System.out.println(">'FCFS' = First-Come, First-Served (ordem da fila).");
            System.out.println(">'PS' = Proccess Status (plano de saude).");
            System.out.println(">'SJF' = Shortest Job First (tempo de vida restante).");
            opc = entrada.next();
            System.out.println();

            System.out.println("[INPUT] deseja o MODO SINCRONIZADO (s/n)?");
            sin = entrada.next().charAt(0);

            if(sin == 's')                                     // verificação de input (sincronização)
                flag = true;
            else if(sin == 'n')
                flag = false;
            else {
                System.out.println("[!!INVALID INPUT [MEDICO]!!]");
                input=false;
            }

            switch(opc){                              // verificação de input (scheduelling/hospital)
                case "PS":
                    hospital = new HospitalPS("Nova Esperanca",flag);
                    break;

                case "FCFS":
                    hospital = new HospitalFCFS("Santana",flag);
                    break;

                case "SJF":
                    hospital = new HospitalSJF("Alberto Camilo",flag);
                    break;

                default:
                    System.out.println("[!!INVALID INPUT!!]");
                    input=false;
                    break;
            }
        }while(!input);

        hospital.addMedicos(num_medicos);               // adiciona a quantida de desejada de medicos

        // ============================== INSERE PACIENTES PARA A FILA ==============================
        hospital.addPaciente(new Paciente("João", 14, 1, 50,30)); //1
        hospital.addPaciente(new Paciente("Maria", 27, 0, 20,10)); // 2
        hospital.addPaciente(new Paciente("Xavier", 63, 3, 90,30)); //3
        hospital.addPaciente(new Paciente("Bruna", 8, 2, 40,20)); //4
        hospital.addPaciente(new Paciente("Ludio", 45, 1, 30,12)); //5
        hospital.addPaciente(new Paciente("Caio", 90, 2, 50,5)); // 6
        hospital.addPaciente(new Paciente("Mateus", 5, 0, 20,30)); //7
        hospital.addPaciente(new Paciente("Henrique", 33, 3, 80,20)); // 8
        hospital.addPaciente(new Paciente("Mariana", 71, 1, 10,28)); //9
        hospital.addPaciente(new Paciente("Helena", 52, 0, 50,15)); // 10
        //hospital.addPaciente(new Paciente("Luisa", 19, 2, 10,12)); // 11
        //hospital.addPaciente(new Paciente("Leo", 88, 3, 30,10)); // 12
        //hospital.addPaciente(new Paciente("Raquel", 42, 1, 10,30)); //13
        //hospital.addPaciente(new Paciente("Samuel", 67, 0, 60,20)); // 14
        //hospital.addPaciente(new Paciente("Zender", 5, 2, 40,15)); //15
        //hospital.addPaciente(new Paciente("Jeremias", 29, 1, 60,20)); //16
        //hospital.addPaciente(new Paciente("Tatiane", 76, 3, 80,30)); //17
        //hospital.addPaciente(new Paciente("Kelly", 55, 0, 10,15)); // 18
        //hospital.addPaciente(new Paciente("Gustavo", 90, 2, 30,30)); //19
        //hospital.addPaciente(new Paciente("Eugenia", 12, 1, 20,30)); //20
        //hospital.addPaciente(new Paciente("Natan", 38, 0, 30,20)); //21
        //hospital.addPaciente(new Paciente("Yuri", 64, 3, 40,26)); //22
        //hospital.addPaciente(new Paciente("Wally", 21, 2, 30,13)); //23
        //hospital.addPaciente(new Paciente("Daniel", 49, 1, 50,4)); // 24
        //hospital.addPaciente(new Paciente("Dolores", 77, 0, 60,21)); //25
        //hospital.addPaciente(new Paciente("Annabeth", 6, 3, 70,16)); //26
        //hospital.addPaciente(new Paciente("Fabio", 53, 2, 90,12)); //27
        //hospital.addPaciente(new Paciente("Heitor", 31, 1, 70,25)); //28
        //hospital.addPaciente(new Paciente("Iara", 85, 0, 30,12)); //29
        //hospital.addPaciente(new Paciente("Jota", 7, 3, 60,28)); // 30

        //=================================== INICIA A SIMULAÇÃO ====================================
        // iniciando a simulação (médicos começam a trabalhar)
        RedutorVida atualizadorTempoDeVida = new RedutorVida(hospital); // inicia a thread redutora
        atualizadorTempoDeVida.start();                                 // de tempo para todos os
                                                                        // pacientes

        hospital.simular();          // inicia a(s) threads dos médicos / MEDICOS COMEÇAM A TRABALHAR

    }
}
