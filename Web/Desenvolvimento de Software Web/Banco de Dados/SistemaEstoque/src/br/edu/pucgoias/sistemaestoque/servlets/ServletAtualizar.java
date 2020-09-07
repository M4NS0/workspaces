package br.edu.pucgoias.sistemaestoque.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.pucgoias.sistemaestoque.controle.EstoqueControle;
import br.edu.pucgoias.sistemaestoque.modelo.Estoque;

/**
 * Servlet implementation class ServletAtualizar
 */
@WebServlet("/ServletAtualizar")
public class ServletAtualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAtualizar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		double precounit = 0;
		double quantidade = 0;
		int id = 0;
		String strPu = request.getParameter("precounit");
		String strQt = request.getParameter("quantidade");
		String strId = request.getParameter("id");
		
		precounit = Double.parseDouble(strPu);
		quantidade = Double.parseDouble(strQt);
		id = Integer.parseInt(strId);
		
		String retorno = "Erro";
		boolean acao = false;
		
		if ((descricao == null || descricao.length() == 0) && id == 0)  retorno = "Descrição inválida";
		else {
			Estoque estoque = new Estoque();
			if ((descricao == null || descricao.length() == 0) && id != 0) {
				EstoqueControle ec = new EstoqueControle();
				acao = ec.excluir(id);
			}
			else {
				estoque.setDescricao(descricao);
				estoque.setPrecounit(precounit);
				estoque.setQuantidade(quantidade);
				estoque.setId(id);
				EstoqueControle ec = new EstoqueControle();
				acao = ec.salvar(estoque);
			}
			if (acao) retorno = "OK";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(" Resposta = " + retorno);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}









