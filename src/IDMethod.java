public enum IDMethod {UUID,TIMESTAMP,SEQUENTIAL,HYBRID,ALPHANUMERIC,SNOWFLAKE;
    public static IDMethod fromString(String value) 
    {
        if(value==null) 
        {
            throw new IllegalArgumentException("Method is required");
        }
        switch(value.toLowerCase()) 
        {
            case"1":
            case"uuid":
                return UUID;
            case"2":
            case"timestamp":
                return TIMESTAMP;
            case"3":
            case"sequential":
                return SEQUENTIAL;
            case"4":
            case"hybrid":
                return HYBRID;
            case"5":
            case"alphanumeric":
                return ALPHANUMERIC;
            case"6":
            case"snowflake":
                return SNOWFLAKE;
            default:
                throw new IllegalArgumentException("Unknown method:"+value);
        }
    }
}
