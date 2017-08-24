package nogu96.streetfighterthirdstrike.model.dao.youtube;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nogu96.streetfighterthirdstrike.internet.DAOException;
import nogu96.streetfighterthirdstrike.internet.HTTPConnectionManager;
import nogu96.streetfighterthirdstrike.internet.ResultListener;


/*<Lo que recibo, lo que nose lo que hace, lo que devuelvo>. La T es un objeto general para que devuelva lo que reciba
de esta manera puedo reutilizar el task*/
public class Task<T> extends AsyncTask<String, Void, T>{

    private ResultListener<T> movieControllerListener;
    private Class<T> containerClass;


    public Task(Class<T> containerClass, ResultListener<T> movieControllerListener){
        this.movieControllerListener = movieControllerListener;
        this.containerClass = containerClass;
    }

    @Override
    protected T doInBackground(String... params) {
        //pido la primera url que estoy pasando
        String url = params[0];

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        HTTPConnectionManager httpConnectionManager = null;

        T request = null;

        try{

            httpConnectionManager = new HTTPConnectionManager();//establesco la conexion a internet
            inputStream = httpConnectionManager.getRequestStream(url);//le pongo la pagita a la pagina para chupar
            bufferedReader = new BufferedReader( new InputStreamReader(inputStream) );//me aseguro que se pueda chupar de manera segura

            Gson gson = new Gson();
            request = gson.fromJson(bufferedReader, containerClass);//me parsea los datos recibidos y lo alamacena de manera magica en la clase MovieTMDBContainer

        }catch (DAOException e){//este error implica que viene del httpConnectionManager.
            e.printStackTrace();
            try{
                if(bufferedReader!=null){//cierro la pagita para liberar espacio en la memoria
                    bufferedReader.close();
                } else if (inputStream != null) {
                    inputStream.close();
                }
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }

        httpConnectionManager.closeConnection();//hay que liberar espacio en memoria

        return request;
    }

    //se ejecuta el onPostExecute cuando termina el doInBackground. Lo utilizo para devolverle el recuest al controller
    @Override
    public void onPostExecute(T request){
        movieControllerListener.finish(request);
    }


}
