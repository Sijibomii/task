DO $$ 
DECLARE 
    table_name TEXT; 
BEGIN 
    FOR table_name IN (SELECT table_name 
                       FROM information_schema.tables 
                       WHERE table_schema = 'public' 
                         AND table_type = 'BASE TABLE') 
    LOOP 
        EXECUTE 'DROP TABLE IF EXISTS "public"."' || table_name || '" CASCADE'; 
    END LOOP; 
END $$;