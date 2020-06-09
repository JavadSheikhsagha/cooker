package jvd.ir.cooker.LoginActivity;

import io.reactivex.Single;
import jvd.ir.cooker.Model.ApiProvider;
import jvd.ir.cooker.Model.ApiService;
import jvd.ir.cooker.Model.MessageModel;

public class LoginApiService {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<MessageModel> userLogin(String userName,String password){
        return apiService.userLogin(userName, password);
    }

    public Single<MessageModel> userRegister(String userName,String password,String mobile){
        return apiService.userRegister(userName, password, mobile);
    }


}
