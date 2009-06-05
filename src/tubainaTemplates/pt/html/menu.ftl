<div class="menu">
	
	<ul class="chapter">
	<#assign i = 1>
	<#list book.chapters as chap>
		<li class="menuItem"><a href="../${relative}/${dirTree[i]}">${chap.title}</a></li>
		<#assign i = i + 1>
		<#list chap.sections as sec>
			<li class="subItem"><a href="../${relative}/${dirTree[i]}">${sec.title}</a></li>
			<#assign i = i + 1>
		</#list>
	</#list>
		<li>&nbsp;</li>
		<li class="menuItem">
			<!-- Begin PayPal Donation button -->
			<div class="donation">
				<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
					<input type="hidden" name="cmd" value="_s-xclick">
					<input type="hidden" name="hosted_button_id" value="2230374">
					<input type="image" src="https://www.paypal.com/en_GB/i/btn/btn_donate_SM.gif" border="0" name="submit" alt="">
					<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
				</form>
			</div>
			<!-- End PayPal Donation button -->
		<li>
	</ul>
	
	
</div>