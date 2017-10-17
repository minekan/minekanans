package sample.sastruts.test.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import sample.sastruts.test.entity.IdTest;
import sample.sastruts.test.form.DbTestForm;
import sample.sastruts.test.service.IdTestService;

public class DbTestAction
{
	@ActionForm
	@Resource
	public DbTestForm dbTestForm;

	@Resource
	public IdTestService idTestService;

	public List<IdTest> resultList;

    @Execute(validator = false)
    public String index()
    {
    	resultList = idTestService.findLikeId(dbTestForm.getId());
        return "index.jsp";
    }

    @Execute(validator = false,urlPattern="newData")
    public String newData()
    {
    	dbTestForm.id="";
    	dbTestForm.val="";
        return "insert.jsp";
    }

    @Execute(validator = false,urlPattern="insertConf")
    public String isertConf()
    {
        return "insertConf.jsp";
    }

    @Execute(validator = false,urlPattern="insert")
    public String insert()
    {
    	IdTest entity = new IdTest();
    	entity.id = dbTestForm.getId();
    	entity.val = dbTestForm.getVal();
    	idTestService.insert(entity);
    	entity.id = "";
    	entity.val = "";
        return "index.jsp";
    }

    @Execute(validator = false,urlPattern="udate")
    public String update()
    {
    	IdTest entity = new IdTest();
    	entity.id = dbTestForm.getId();
    	entity.val = dbTestForm.getVal();
    	idTestService.update(entity);
        return "index.jsp";
    }

    @Execute(validator = false,urlPattern="detail")
    public String select()
    {
    	IdTest entity = idTestService.findById(dbTestForm.getId());
    	dbTestForm.setId(entity.id);
    	dbTestForm.setVal(entity.val);
        return "detail.jsp";
    }

}
