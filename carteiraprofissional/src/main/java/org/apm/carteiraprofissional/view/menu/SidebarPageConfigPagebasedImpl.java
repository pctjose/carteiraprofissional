package org.apm.carteiraprofissional.view.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apm.carteiraprofissional.Utilizador;
import org.apm.carteiraprofissional.view.SidebarPage;
import org.apm.carteiraprofissional.view.SidebarPageConfig;
import org.zkoss.zk.ui.Sessions;

public class SidebarPageConfigPagebasedImpl implements SidebarPageConfig {

	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigPagebasedImpl(){		
		
		Utilizador logedInUser = (Utilizador)Sessions.getCurrent().getAttribute("logedInUser");
		
		pageMap.put("zk",new SidebarPage("zk","www.zkoss.org","/imgs/site.png","http://www.zkoss.org/"));
		pageMap.put("demo",new SidebarPage("demo","ZK Demo","/imgs/demo.png","http://www.zkoss.org/zkdemo"));
		pageMap.put("devref",new SidebarPage("devref","ZK Developer Reference","/imgs/doc.png"
				,"http://books.zkoss.org/wiki/ZK_Developer's_Reference"));
		
		if(logedInUser!=null){
			if(logedInUser.getGrupo().getUuid().equalsIgnoreCase("6b9f55b5-e73d-11e3-8e8f-a4db30f2439a")){
				//NORMAL
				pageMap.put("fn1",new SidebarPage("fn1","Requisitar Carteira","/imgs/fn.png"
						,"/pages/PaginaEmConstrucao.zul"));
				pageMap.put("fn2",new SidebarPage("fn2","Actualizar Formação Academica","/imgs/fn.png"
						,"/pages/PaginaEmConstrucao.zul"));
				pageMap.put("fn3",new SidebarPage("fn3","Actualizar Formação Profissional","/imgs/fn.png"
						,"/pages/PaginaEmConstrucao.zul"));
				pageMap.put("fn4",new SidebarPage("fn4","Actualizar Experiência Profissional","/imgs/fn.png"
						,"/pages/PaginaEmConstrucao.zul"));
				pageMap.put("fn5",new SidebarPage("fn5","Requisitar Actualização da Carteira","/imgs/fn.png"
						,"/pages/PaginaEmConstrucao.zul"));
				pageMap.put("fn6",new SidebarPage("fn6","Actualizar Perfil","/imgs/fn.png"
						,"/pages/pagebased/index-utilizador-novo.zul"));
			}else
				if(logedInUser.getGrupo().getUuid().equalsIgnoreCase("6b9a194d-e73d-11e3-8e8f-a4db30f2439a")){
					//APM
					pageMap.put("fn1",new SidebarPage("fn1","Administração de Utilizadores","/imgs/fn.png"
							,"/pages/pagebased/index-utilizador-lista.zul"));
					pageMap.put("fn2",new SidebarPage("fn2","Administração de Requisições de Carteiras","/imgs/fn.png"
							,"/pages/pagebased/index-requisicao-nova.zul"));
					pageMap.put("fn3",new SidebarPage("fn3","Administração de Carteiras","/imgs/fn.png"
							,"/pages/pagebased/index-todolist-mvc.zul"));
					pageMap.put("fn4",new SidebarPage("fn4","Administração de Relatórios","/imgs/fn.png"
							,"/pages/pagebased/index-todolist-mvvm.zul"));
					
				}else{
					pageMap.put("fn1",new SidebarPage("fn1","Emitir Carteira","/imgs/fn.png"
							,"/pages/pagebased/index-utilizador-lista.zul"));
					pageMap.put("fn2",new SidebarPage("fn2","Actualizar Carteira Emitida","/imgs/fn.png"
							,"/pages/pagebased/index-profile-mvvm.zul"));
					
				}
		}
		
		
		
		
		

		
	}

	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}

	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}

}
