package br.ufc.dc.dspm.mynotes;

public class Note {

    private int id;
    private String title;
    private String content;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "(" + id + ") " + title +"\t - " + getDataFormatada() + ":\n" + content;
    }

    public String getData() {
        return data;
    }

    public String getDataFormatada(){
        String retorno = "";
        if(data!=null){
            String[] aux = data.split("-");
            if(aux != null && aux.length ==3){
                retorno+=aux[2]+"/";
                retorno+=aux[1]+"/";
                retorno+=aux[0];
            }
        }
        return retorno;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setDataFormatada(String dataf){
        data = "";
        String aux[] = dataf.split("/");
        if(aux != null && aux.length == 3){
            data+=preenche(aux[2],4)+"-";
            data+=preenche(aux[1],2)+"-";
            data+=preenche(aux[0],2);
        }
    }
    private String preenche(String cadeia,int tam){
        String retorno = cadeia;
        for(int i = cadeia.length(); i < tam ; i++)
            retorno = "0"+retorno;
        return retorno;
    }
}