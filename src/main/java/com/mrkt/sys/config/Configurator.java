package com.mrkt.sys.config;

import java.util.Properties;
import java.io.InputStreamReader;

public class Configurator
{
  // CONSTRUCT
    public static final String RES_PUBLIC_CONFIG = "/staticconfig.pub.properties";
    public static final String RES_PRIVATE_CONFIG = "/staticconfig.properties";

    public static final String RES_WX_CONFIG = "/wx.config.properties";
    
    protected Properties publicProp;
    protected Properties prop;

    private Configurator()
    {
        this.load();
    }

  // DEFAULT INSTANCE
    private static class DefaultInstance
    {
        public static Configurator instance = new Configurator();
    }

    public static Configurator getInstance()
    {
        return(DefaultInstance.instance);
    }

  //
    /** 加载配置文件
     */
    public void load()
    {
        try
        {
            InputStreamReader is;

          // PUBLIC
            is = new InputStreamReader(
                this.getClass()
                    .getResourceAsStream(RES_PUBLIC_CONFIG),
                "utf-8"
            );

            this.publicProp = new Properties();
            this.publicProp.load(is);
            is.close();

            is = new InputStreamReader(
                    this.getClass()
                        .getResourceAsStream(RES_WX_CONFIG),
                    "utf-8"
                );
            
            this.publicProp = new Properties(this.publicProp);
            this.publicProp.load(is);
            is.close();
            
          // PRIVATE
            is = new InputStreamReader(
                this.getClass()
                    .getResourceAsStream(RES_PRIVATE_CONFIG),
                "utf-8"
            );

            this.prop = new Properties(this.publicProp);
            this.prop.load(is);
            is.close();
            

        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex.getMessage(), ex));
        }
    }

    /** 获得整个属性库的只读副本
     * 本质上是将原有 prop 作为默认表创建新表并返回.
     * 因此, 对得到的表作出的修改不会影响到原表.
     */
    public Properties getProperties()
    {
        Properties prop = new Properties(this.prop);

        return(prop);
    }

    /** 获得公开属性库的只读副本
     * 本质上是将原有 prop 作为默认表创建新表并返回.
     * 因此, 对得到的表作出的修改不会影响到原表.
     * 主要用于将服务器端设置值告诉客户端.
     */
    public Properties getPublicProperties()
    {
        Properties prop = new Properties(this.publicProp);

        return(prop);
    }


    /**
     * 提取配置文件中的参数
     */
    public String get(String name)
    {
        return(
            this.prop.getProperty(name)
        );
    }

    public String get(String name, String defaultValue)
    {
        String value = this.prop.getProperty(name);

        return(
            value!=null ? value : defaultValue
        );
    }


    public Integer getInt(String name)
    {
        try
        {
            String value = this.get(name);
            return(
                value!=null ? Integer.valueOf(value) : null
            );
        }
        catch (Exception ex)
        {
            return(null);
        }
    }

    public Integer getInt(String name, Integer defaultValue)
    {
        Integer value = this.getInt(name);
        return(
            value!=null ? value : defaultValue
        );
    }

    public Long getLong(String name)
    {
        try
        {
            String value = this.get(name);
            return(
                value!=null ? Long.valueOf(value) : null
            );
        }
        catch (Exception ex)
        {
            return(null);
        }
    }

    public Long getLong(String name, Long defaultValue)
    {
        Long value = this.getLong(name);
        return(
            value!=null ? value : defaultValue
        );
    }

    public Double getDouble(String name)
    {
        try
        {
            String value = this.get(name);
            return(
                value!=null ? Double.valueOf(value) : null
            );
        }
        catch (Exception ex)
        {
            return(null);
        }
    }

    public Double getDouble(String name, Double defaultValue)
    {
        Double value = this.getDouble(name);
        return(
            value!=null ? value : defaultValue
        );
    }

    public Boolean getBoolean(String name)
    {
        try
        {
            String value = this.get(name);
            return(
                value!=null ? Boolean.valueOf(value) : null
            );
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return(null);
        }
    }

    public Boolean getBoolean(String name, Boolean defaultValue)
    {
        Boolean value = this.getBoolean(name);
        return(
            value!=null ? value : defaultValue
        );
    }
}
