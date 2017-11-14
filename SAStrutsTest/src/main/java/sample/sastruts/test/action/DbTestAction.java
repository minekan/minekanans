package sample.sastruts.test.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import net.arnx.jsonic.JSON;
import sample.sastruts.test.entity.IdTest;
import sample.sastruts.test.form.DbTestForm;
import sample.sastruts.test.service.IdTestService;

public class DbTestAction
{
	@ActionForm
	@Resource
	public DbTestForm dbTestForm;

	@Resource
	HttpServletRequest req;

	@Resource
	public IdTestService idTestService;

	public List<IdTest> resultList;

    @Execute(validator = false)
    public String index()
    {
    	resultList = idTestService.findLikeId(dbTestForm.getId());
        return "index.jsp";
    }
    @Execute(validator = false,urlPattern="load")
    public String load_json() throws IOException {
    	String id = req.getParameter("id");
        //データベースからデータを全件取得します。
    	List<IdTest> result = idTestService.findLikeId(id);

        HttpServletResponse httpServletResponse = ResponseUtil.getResponse();
        httpServletResponse.setContentType("application/json");
        PrintWriter sendPoint = new PrintWriter(httpServletResponse.getOutputStream());

        //Entity→JSON形式に変換して出力します。
        sendPoint.println(JSON.encode(result));
        sendPoint.flush();
        sendPoint.close();
        return null;
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
