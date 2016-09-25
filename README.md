# DomainManager
主要用来管理测试环境和生产环境的server domain，以及page url.

###1.自定义ServerDomainType
```
public enum HxServerDomainType implements ServerDomainType {
    NOTE,
}
```
###2.自定义ServerDomainConfig
```
public class HxServerDomainConfig {
    private static Map<ServerDomainType, String> PRODUCT = new HashMap<ServerDomainType, String>() {{
        put(CommonServerDomainType.SSO, "http://baidao.com.sso");
        put(HxServerDomainType.NOTE, "http://private-f61f90-login44.apiary-mock.com/");
    }};

    private static Map<ServerDomainType, String> TEST = new HashMap<ServerDomainType, String>() {{
        put(CommonServerDomainType.SSO, "http://test.baidao.com.sso");
        put(HxServerDomainType.NOTE, "https://private-28d044-peel1.apiary-mock.com/");
    }};

    static Map<ServerDomainType, String> getDomain(boolean isDebug) {
        if (isDebug) {
            return TEST;
        }
        return PRODUCT;
    }
}
```
###3.实现ServerDomainFactory
```
 public class HxServerDomainFactory extends ServerDomainFactory {

    @Override
    public Map<ServerDomainType, String> getDomain(Context context, boolean isDebug) {
        return HxServerDomainConfig.getDomain(isDebug);
    }
}
```
###4.在application的onCreate方法中，初始化DomainUtil
```
@Override
public void onCreate() {
    super.onCreate();

    initDomainConfig();

}

private void initDomainConfig() {
    DomainUtil.initialize(this, new HxPageDomainFactory(), new HxServerDomainFactory());
    DomainUtil.setIsDebug(BuildConfig.DEBUG);
} 
```
###4.访问api
```
NoteApi noteApi = ServiceFactory.getInstance().create(HxServerDomainType.NOTE, NoteApi.class);
```
###5.在测试环境和生产环境之前动态切换
```
DomainUtil.setIsDebug(debug);
ServiceFactory.getInstance().clearCache();
```
