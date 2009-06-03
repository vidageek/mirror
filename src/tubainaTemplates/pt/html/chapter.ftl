<#assign relative = "..">

<#include "header.ftl">

		<h1 class="chapter">${chapter.title}</h1>

		${chapter.getIntroduction(parser)}
		
		<#list chapter.sections as section>
			<#include "sectionContent.ftl">		
		</#list>

<#include "footer.ftl">