package control;

import models.*;
import services.DAO;
import services.GetDadosAgencia;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public List<DTO> getDTOinFile() {
        DAO dao = new DAO();
        List<DTO> listaDTO;
        listaDTO = dao.ReadFile();
        return listaDTO;
    }

    public List<Agencia> getAgenciasFromListaDTO(List<DTO> listaDTO) {
        List<Agencia> agencias;
        GetDadosAgencia dadosAgencia = new GetDadosAgencia();
        agencias = dadosAgencia.getDados(listaDTO);
        return agencias;
    }

    public static List<Conta> getContasFromAgencias(List<Agencia> agencias) {
        List<Conta> contas = new ArrayList<>();
        Conta conta = new Conta();
        Agencia agencia;

        for (int i = 0; i < agencias.size(); i++) {
            agencia = agencias.get(i);

            for (int j = 0; j < agencia.getContas().size(); j++) {
                conta = agencia.getContas().get(j);
            }
            contas.add(conta);
        }
        return contas;
    }

    public static List<Transacao> getTransacoesFromContas(List<Conta> contas) {
        List<Transacao> transacoes = new ArrayList<>();
        Conta conta;
        Transacao transacao = new Transacao();

        for (int i = 0; i < contas.size(); i++) {
            conta = contas.get(i);
            for (int j = 0; j < conta.getTransacoes().size(); j++) {
                transacao = conta.getTransacoes().get(j);
            }
            transacoes.add(transacao);
        }
        return transacoes;
    }

    public static List<Resultado> getResultadosFromTransacoes(List<Transacao> transacoes) {
        List<Resultado> resultados = new ArrayList<>();
        Transacao transacao;
        Resultado resultado = new Resultado();

        for (int i = 0; i < transacoes.size(); i++) {
            transacao = transacoes.get(i);
            for (int j = 0; j < transacao.getResultados().size(); j++) {
                resultado = transacao.getResultados().get(j);
            }
            resultados.add(resultado);
        }
        return resultados;
    }

    public static List<Permissao> getResultadosPermitidos(List<Resultado> resultados) {
        List<Permissao> permissoes = new ArrayList<>();
        Resultado resultado;
        Permissao permissao  = new Permissao();

        for (int i = 0; i < resultados.size(); i++) {
            resultado = resultados.get(i);
            if (resultado.getTransacoesPermitidas() != null) {
                for (int j = 0; j < resultado.getTransacoesPermitidas().size(); j++) {
                    permissao = resultado.getTransacoesPermitidas().get(j);
                }
                permissoes.add(permissao);
            }
        }
        return permissoes;
    }

    public static List<Permissao> getResultadosNegados(List<Resultado> resultados) {
        List<Permissao> permissoes = new ArrayList<>();
        Resultado resultado;
        Permissao permissao  = new Permissao();

        for (int i = 0; i < resultados.size(); i++) {
            resultado = resultados.get(i);
            if ( resultado.getTransacoesNegadas() != null) {
                for (int j = 0; j < resultado.getTransacoesNegadas().size(); j++) {
                    permissoes.add(resultado.getTransacoesNegadas().get(j));
                }
                permissoes.add(permissao);
            }
        }
        return permissoes;
    }

    public static void gravaCSVpermitidos(List<Permissao> permitidos) {
        Controller controler = new Controller();
        controler.putPermitidosInCSV(permitidos);
    }
    public static void gravaCSVnegados(List<Permissao> negados) {
        Controller controler = new Controller();
        controler.putNegadosInCSV(negados);
    }

    public void putNegadosInCSV(List<Permissao> negados) {
        DAO dao = new DAO();
        dao.WriteFileNegados(negados);
    }

    public void putPermitidosInCSV(List<Permissao> permitidos) {
        DAO dao = new DAO();
        dao.WriteFilePermitidos(permitidos);
    }








}
