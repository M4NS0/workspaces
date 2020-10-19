package br.com.cadastro.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.cadastro.modelo.Funcionario;
import br.com.cadastro.persistencia.FuncionarioDao;

@WebServlet("/ServletConsultaPorDescricao")
public class ServletConsultaPorDescricao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public ServletConsultaPorDescricao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pesquisar = request.getParameter("pesquisar");

		gson = new GsonBuilder().setPrettyPrinting().create();
		Funcionario func;
		List<Funcionario> lista = new ArrayList<>();

		FuncionarioDao funcDao = new FuncionarioDao();
		lista = funcDao.getNome(pesquisar);

		String retornoJsonString = this.gson.toJson(lista);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(retornoJsonString);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
