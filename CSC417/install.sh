if   which java
   then echo "# java installed" 
   else sudo apt install default-jre 
   fi
if   which kotlin
   then echo "# kotlin installed"
   else sudo snap install --classic kotlin
   fi
done
