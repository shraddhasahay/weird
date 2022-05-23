package BankerAlgorithm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankerAlgo
 */

@WebServlet("/BankerAlgo")
public class BankerAlgo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int alloc[][] = new int[5][4];
	int max[][] = new int[5][4];
	int need[][] = new int[5][4];
	int avl[] = new int[4];
	int safestate[] = new int[20];

	public BankerAlgo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		try {

			out = response.getWriter();

			String[] m = request.getParameterValues("m");
			String[] a = request.getParameterValues("a");
			String[] av = request.getParameterValues("av");
			int k = 0, l = 0;
			out.println("<center>");
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 4; j++) {
					alloc[i][j] = Integer.parseInt(a[k++]);
					max[i][j] = Integer.parseInt(m[l++]);

				}

			}
			for (int i = 0; i < 4; i++)
				avl[i] = Integer.parseInt(av[i]);
			
			if (safety()==true) 
			{
				
				request.setAttribute("data",safestate);
				RequestDispatcher dispatcher=request.getRequestDispatcher("Result.jsp");
				dispatcher.forward(request, response);
				
			} 
			else if(safety()==false) {
				
				request.setAttribute("data",safestate);
				RequestDispatcher dispatcher=request.getRequestDispatcher("Deadlock.jsp");
				dispatcher.forward(request, response);
				
				//out.println("SYSTEM IS IN UNSAFE STATE");

			}
			
		} catch (Exception e) {
			out.println("ERROR:" + e.getMessage());
		} 
		finally {
			out.println("<br><br>");
			out.println("To go to main page<a href=index.html> Click Heere</a>");
			out.println("</center>");
		}

	}

	boolean safety() {
		int n = 5, i, j;
		int m = 4;
		
		for (i = 0; i < n; i++)
			for (j = 0; j < m; j++) {
				need[i][j] = max[i][j] - alloc[i][j];
			}
		for(i=0;i<20;i++)
			safestate[i]=-1;
		boolean fin[] = new boolean[n];

		for (i = 0; i < n; i++)
			fin[i] = false;

		int k=0;
		int check1 = 0;

		do {
			for (i = 0; i < n; i++) 
			{
				boolean flag = true;
				if (fin[i] == false)
				{
					for (j = 0; j < m; j++) 
					{
						if (avl[j] < need[i][j])
							{flag = false; break;}
					}
					if (flag) 
					{
						for (j = 0; j < m; j++)
							avl[j] += alloc[i][j];
						safestate[k++] = i;
						
						fin[i] = true;
					}
				}
				

			}

			check1++;
		} while ( check1 < n && k<n);
		if (k < n)
			return false;
		else
			return true;
	}
}
