# unused
curl \
-d "partner_guid=E872C58A-15C5-AD6F-99DC-3C81565EA71E" \
-d "action=createAccount" \
-d "first_name=firsttest" \
-d "last_name=lasttest" \
-d "program_id=1" \
-d "coverage_period_id=1" \
-d "email_address=tstockett@solutionstream.com" \
-d "password=password" \
-d "password_hint=password" \
-d "start_date=05/01/2012" \
-d "dob=01/01/1990" \
-d "address_1=500 Somestreet" \
-d "city=Somecity" \
-d "state_id=1" \
-d "zip=55555" \
-d "country_id=1" \
-d "creditcard=1111222233334444" \
-d "expiration=03/2014" \
-d "ccv=405" \
-d "bill_address_1=500 Somestreet" \
-d "bill_city=Somecity" \
-d "bill_state_id=1" \
-d "bill_zip=55555" \
-d "bill_country_id=1" \
-d "partner_product=1" \
http://staging.globalrescue.com/api/index.cfm
