package abap_quickfix_s4_vna_plugin;

import java.util.ArrayList;

import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;

import com.abapblog.adt.quickfix.IFixAppender;
import com.abapblog.adt.quickfix.assist.syntax.statements.StatementAssist;

import ddic.DDICChange;

public class FixAppender implements IFixAppender {

    @Override
    public ArrayList<StatementAssist> additional_fixes(IQuickAssistInvocationContext context) {
        ArrayList<StatementAssist> list = new ArrayList<StatementAssist>();
        
        list.add(new DDICChange());
        
        return list;
    }

}
