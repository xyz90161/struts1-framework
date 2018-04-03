# struts1-json-framework
基於struts1</br>
CommonAction類別繼承原Action類別後實作execute方法並在execute方法內加上錯誤處理與回傳json處理</br>
繼承CommonAction的類別因CommonAction實作ICommonAction介面並將doProcess的方法設為抽象方法，</br>
但繼承CommonAction的類別必須實作doProcess的方法</br>
拋出的錯誤類別若為CommonException類別的子類別則會取出錯誤代碼與錯誤訊息後拋出錯誤</br>
若為未定義的錯誤則會拋出rc:9999,rm:系統未定義的錯誤</br>
