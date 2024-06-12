package ddic;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

import com.abapblog.adt.quickfix.assist.syntax.statements.IAssistRegex;
import com.abapblog.adt.quickfix.assist.syntax.statements.StatementAssistRegex;
import com.abapblog.adt.quickfix.assist.syntax.statements.move.MoveExact;

import abap_quickfix_s4_vna_plugin.Activator;

public class BSAD_BISD_View extends StatementAssistRegex implements IAssistRegex {

    private static final String selectPattern =
            " ((bsad)|(bsid))";

    private static final String replaceByPattern = " $1_view";


    public BSAD_BISD_View() {
        super();

        IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.eclipse.ui.editors");
        Boolean bool = preferences.getBoolean(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_SPACES_FOR_TABS,
                true);
        int tabsno = preferences.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH, 4);
        System.out.println("preferences are: " + bool + tabsno);
    }

    @Override
    public String getChangedCode() {
 
        String temp3 = CodeReader.CurrentStatement.replaceAllPattern("\r\n\\s*[\r\n]", ""); // remove first line feed

        return temp3.replaceAll(getMatchPattern(), getReplacePattern());
    }

    

    @Override
    public String getAssistShortText() {
        return "Change Type bsad, bsid to *_view";
    }

    @Override
    public String getAssistLongText() {
        return "Changes Replaced Object Type BSAD, BSID to bsad_view, bsid_view";
    }

    private static Image icon;

   
 

    @Override
    public boolean canAssist() {
    	String temp1 = CodeReader.CurrentStatement.getStatement();
        if (CodeReader.CurrentStatement.matchPatternSingleLine(getMatchPattern())) {

            // get current indentation for statement, prefix with line break
            String temp = CodeReader.CurrentStatement.replacePattern(getMatchPattern(), "$1");
            // currentIndent = System.lineSeparator().concat(temp.replaceAll("\\s*\\R",
            // ""));// remove spaces

            return true;
        }
        return false;
    }

    @Override
    public int getStartOfReplace() {
        return CodeReader.CurrentStatement.getBeginOfStatement();
    }

    @Override
    public int getReplaceLength() {
        return CodeReader.CurrentStatement.getStatementLength();
    }

    @Override
    public String getMatchPattern() {
        return selectPattern;

    }

    @Override
    public String getReplacePattern() {
        return replaceByPattern;
    }

}
