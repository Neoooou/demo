package com.example.machine_learning;

import com.aliyun.openservices.eas.predict.http.HttpConfig;
import com.aliyun.openservices.eas.predict.http.PredictClient;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.thymeleaf.util.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: neo.zr
 * @Date: created on 2020/12/10
 */

public class Prophet {

    public static void main(String[] args) {
        callPredictionWithHttp();
        InnerModel model = new InnerModel();
        model.predict();

    }


    public static void callPredictionWithHttp(){
        //启动并初始化客户端
        PredictClient client = new PredictClient(new HttpConfig());
        /*
         * 如果Internet Endpoint为http://eas-shanghai.alibaba-inc.com/api/predict/credit
         * 则client.setEndpoint("eas-shanghai.alibaba-inc.com");
         * client.setModelName("credit")
         * client.setToken需要根据服务具体生成的token设置
         */
        client.setToken("ZjhmZDdhMTBlMTg4N2FlNGEzOGM0ZjY1MTRkZGJhN2IxYTg1ZmRlZA==");
        client.setEndpoint("eas-shanghai.alibaba-inc.com");
        client.setModelName("test_score1");

        //输入字符串定义
        String request = "[{\"c0\": 1, \"c1\":1, \"c2\":1, \"c3\":1}]";
        System.out.println(request);

        //通过eas返回字符串
        String response = client.predict(request);
        System.out.println(response);

        //关闭客户端
        client.shutdown();
        return;
    }

    static class InnerModel{
        Evaluator evaluator;
        InnerModel(){
            this.evaluator = loadModel();
        }

        public Evaluator loadModel(){
            PMML pmml = new PMML();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream("/Users/zhangran/idea-projects/demo/src/main/resources/lr_demo.pmml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(inputStream == null){
                return null;
            }
            InputStream is = inputStream;
            try {
                pmml = org.jpmml.model.PMMLUtil.unmarshal(is);
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }finally {
                //关闭输入流
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
            Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            pmml = null;
            return evaluator;
        }

        public void predict(){
            Map<FieldName, Integer> param = new HashMap<>();
            param.put(new FieldName("c0"), 1);
            param.put(new FieldName("c1"), 1);
            param.put(new FieldName("c2"), 1);
            param.put(new FieldName("c3"), 1);

            Map<FieldName, ?> result = this.evaluator.evaluate(param);
            System.out.println(result);
            System.out.println(StringUtils.repeat("-", 10));

            result.forEach(
                    (k,v)->{
                        System.out.println(k + " " + v);
                        System.out.println(k + " " + v);
                    }
            );
        }
    }
}
