curl \
-d "partner_guid=E872C58A-15C5-AD6F-99DC-3C81565EA71E" \
-d "action=addFamilyMember" \
-d "account_guid=D187910E-92A5-D377-FD7426374E5B2897" \
-d "first_name=namef" \
-d "last_name=lasttest" \
-d "member_type=student" \
-d "family_relationship=daughter" \
http://staging.globalrescue.com/api/index.cfm


#-d "account_guid=D187910E-92A5-D377-FD7426374E5B2897" -> good even though account canceled
#-d "account_guid=F187910E-92A5-D377-FD7426374E5B2897" -> account does not exist 
