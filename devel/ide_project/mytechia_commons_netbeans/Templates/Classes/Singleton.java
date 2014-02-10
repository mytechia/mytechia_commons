<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "../Licenses/license-${project.license}.txt">

<#if package?? && package != "">

package ${package};

</#if>
/**
 *
 * @author ${user}
 */
public class ${name} 
    {

    private ${name}() 
    {

    }

    public static ${name} getInstance() 
    {
        return ${name}Holder.INSTANCE;
    }

    private static class ${name}Holder 
    {
        private static final ${name} INSTANCE = new ${name}();
    }
 }