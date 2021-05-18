package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao(); // injecao de dependencia, para pegar o dados da tabela
	
	public List<Department> findaAll(){ 
		return dao.findAll();
			
		//MOKA os dados, retornar os dados de mentirinha
		/*List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Eletronics"));
		return list;*/
	}

}
