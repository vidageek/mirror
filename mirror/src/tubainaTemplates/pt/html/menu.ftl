<div class="menu">
	
	<ul class="chapter">
	<#assign i = 1>
	<#list book.chapters as chap>
		<li><a href="../${relative}/${dirTree[i]}">${chap.title}</a></li>
		<#assign i = i + 1>
		<ul class="section">
		<#list chap.sections as sec>
			<li><a href="../${relative}/${dirTree[i]}">${sec.title}</a></li>
			<#assign i = i + 1>
		</#list>
		</ul>	
	</#list>
	</ul>
	
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

</div>