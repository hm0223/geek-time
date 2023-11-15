package com.hm.geek.beauty.design.ch41_ch73.structural.proxy;



/**
 * IUserController.
 * 
 * @author huwenfeng
 */
public interface IUserController {
    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}

class UserController implements IUserController {
    //...省略其他属性和方法...

    @Override
    public UserVo login(String telephone, String password) {
        //...省略login逻辑...
        //...返回UserVo数据...
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        //...省略register逻辑...
        //...返回UserVo数据...
        return null;
    }
}

class UserControllerProxy implements IUserController {
    private final MetricsCollector metricsCollector;
    private final UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        // 委托
        UserVo userVo = userController.login(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = userController.register(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }


    public static void main(String[] args) {
        // UserControllerProxy使用举例
        // 因为原始类和代理类实现相同的接口，是基于接口而非实现编程
        // 将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码
        IUserController userController = new UserControllerProxy(new UserController());
    }
}

