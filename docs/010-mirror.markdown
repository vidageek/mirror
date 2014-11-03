---
permalink: /index.html
title: Mirror
---

Mirror was created to bring light to a simple problem, usually named ReflectionUtil, which 
is on almost all projects that rely on reflection to do advanced tasks.

Dealing with Java Reflection API is painful. Ask anyone you know that uses reflection and
he will tell you it's really unpleasant getting yourself around it.

Take a look at the following code:

    //Let's just set a field value. Should be a simple task, right?
    
    //"target" is the object containing the field whose value you want to set. 
    Field toSet = null;
    for (Field f : target.getClass().getDeclaredFields()) { 
    	//Get all fields DECLARED inside the target object class 
    	if (f.getName().equals("field")) {
    		toSet = f;
    	}
    }
    if (toSet != null && (toSet.getModifiers() & Modifier.STATIC) == 0) {
    	toSet.setAccessible(true);
    	toSet.set(target, value);
    }

Ah! What an obfuscated code! Completely unreadable. What is `Modifier.STATIC` doing in my code?

So, what a good programmer would do? Hide it inside some \*Util class, so he would be able to
use it like this:

    ReflectionUtil.setField(target, fielName, value);

Better, but completely procedural. Not good.

Wouldn't be much better if you could just do something like this:

    Dear program,
    
    On a target object, set fieldName with value.

Beautiful, isn't?

Taking away all those sugar-full words, we get:

    new Mirror().on(target).set().field(fieldName).withValue(value);

And this was our "You don't even know it's a tutorial" Tutorial. Mirror is that simple.
