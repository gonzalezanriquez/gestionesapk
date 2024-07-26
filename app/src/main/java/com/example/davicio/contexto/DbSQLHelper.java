    package com.example.davicio.contexto;
    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import com.example.davicio.entidades.Productos;
    import com.example.davicio.entidades.Sucursales;
    import com.example.davicio.entidades.Usuarios;

    import java.util.ArrayList;
    import java.util.LinkedHashMap;
    import java.util.Map;
    public class DbSQLHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "gestiones";
        private static final int DATABASE_VERSION = 1;

        //USUARIOS
        private static final String TABLE_USUARIO = "usuario";
        private static final String COLUMN_USUARIO_ID = "id";
        private static final String COLUMN_USUARIO_NOMBRE = "nombre";
        private static final String COLUMN_USUARIO_APELLIDO = "apellido";
        private static final String COLUMN_USUARIO_MAIL = "mail";
        private static final String COLUMN_USUARIO_CONTRASENIA = "contrasenia";

        // PRODUCTOS
        private static final String TABLE_PRODUCTO = "producto";
        private static final String COLUMN_PRODUCTO_ID = "id";
        private static final String COLUMN_PRODUCTO_NOMBRE = "nombre";
        private static final String COLUMN_PRODUCTO_DESCRIPCION = "descripcion";
        private static final String COLUMN_PRODUCTO_PRECIO = "precio";
        private static final String COLUMN_PRODUCTO_USUARIO_ID = "usuario_id";

        // SUCURSALES
        private static final String TABLE_SUCURSAL = "sucursales";
        private static final String COLUMN_SUCURSAL_ID = "id";
        private static final String COLUMN_SUCURSAL_NOMBRE = "nombre";
        private static final String COLUMN_SUCURSAL_DIRECCION = "direccion";
        private static final String COLUMN_SUCURSAL_TELEFONO = "telefono";



        public DbSQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*TABLA USUARIOS---------------------------------------------------------------------------------------*/
            String createUsuarioTableQuery = "CREATE TABLE " + TABLE_USUARIO + "("
                    + COLUMN_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USUARIO_NOMBRE + " TEXT,"
                    + COLUMN_USUARIO_APELLIDO + " TEXT,"
                    + COLUMN_USUARIO_MAIL + " TEXT,"
                    + COLUMN_USUARIO_CONTRASENIA + " TEXT" + ")";
            db.execSQL(createUsuarioTableQuery);

            /*TABLA PRODUCTOS---------------------------------------------------------------------------------------*/
            String createProductosTableQuery = "CREATE TABLE " + TABLE_PRODUCTO + "("
                    + COLUMN_PRODUCTO_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCTO_NOMBRE + " TEXT,"
                    + COLUMN_PRODUCTO_DESCRIPCION + " TEXT,"
                    + COLUMN_PRODUCTO_PRECIO + " REAL,"
                    + COLUMN_PRODUCTO_USUARIO_ID + " INTEGER,"
                    + "FOREIGN KEY (" + COLUMN_PRODUCTO_USUARIO_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_USUARIO_ID + ")" + ")";
            db.execSQL(createProductosTableQuery);

            /*TABLA SUCRUSALES---------------------------------------------------------------------------------------*/
            String createSucursalTableQuery = "CREATE TABLE " + TABLE_SUCURSAL + "("
                    + COLUMN_SUCURSAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SUCURSAL_NOMBRE + " TEXT,"
                    + COLUMN_SUCURSAL_DIRECCION + " TEXT,"
                    + COLUMN_SUCURSAL_TELEFONO + " TEXT"
                    + ")";
            db.execSQL(createSucursalTableQuery);

            /*EJEMPLOS USUARIOS---------------------------------------------------------------------------------------*/
            String usu1 = precargados("Admin","admin","mail","1234");
            String usu2 = precargados("Leandro","Gonzalez","mail1","1234");
            String usu3 = precargados("Carolina","Mangialavori","mail2","1234");
            String usu4 = precargados("Camila","Giudice","mail3","1234");
            String usu5 = precargados("Leonel","Cortecci","mail4","1234");
            String usu6 = precargados("Kevin","Hercog","mail5","1234");
            db.execSQL(usu1);
            db.execSQL(usu2);
            db.execSQL(usu3);
            db.execSQL(usu4);
            db.execSQL(usu5);
            db.execSQL(usu6);
            /*EJEMPLOS PRODUCTOS---------------------------------------------------------------------------------------*/
            String prod1=productosprecargados("CLIPS","2 cajas para renovacion de secretaria plazo 2 meses","5000");
            String prod2=productosprecargados("RESMAS","10 paquetes para impresion de boletines e informes de nivel primario y secundario","50.000");
            String prod3=productosprecargados("ABROCHADORAS","2 nuevas abrochadoras para direccion de primaria y secundaria que necesitan para la Gala de Arte","15.000");
            db.execSQL(prod1);
            db.execSQL(prod2);
            db.execSQL(prod3);
            /*EJEMPLOS SUCURSALES---------------------------------------------------------------------------------------*/
            String sucu1=precargadossucursales("COLEGIO SEDE CENTRAL","Av. Corrientes 2037, CABA","1136877515");
            String sucu2=precargadossucursales("COLEGIO SEDE CABALLITO","Formosa 293, CABA","1136877515");
            String sucu3=precargadossucursales("COLEGIO SEDE ABASTO","Avenida Corrientes 3247, CABA","1136877515");
            String sucu4=precargadossucursales("COLEGIO SEDE PALERMO","Av. Sta. Fe 3253, CABA","1136877515");
            String sucu5=precargadossucursales("COLEGIO SEDE MORON","Belgrano 250, Moron","1136877515");
            String sucu6=precargadossucursales("COLEGIO SEDE MORENO","Moron 1223, Paso del Rey","1136877515");
            db.execSQL(sucu1);
            db.execSQL(sucu2);
            db.execSQL(sucu1);
            db.execSQL(sucu3);
            db.execSQL(sucu4);
            db.execSQL(sucu5);
            db.execSQL(sucu6);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        /*CREATE*/
        public Boolean insertUsuario(String nombre, String apellido, String mail, String contrasenia) {

            if(usuarioRegistrado(mail)){
                return false;
            }else{
                SQLiteDatabase db = getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(COLUMN_USUARIO_NOMBRE, nombre);
                registro.put(COLUMN_USUARIO_APELLIDO, apellido);
                registro.put(COLUMN_USUARIO_MAIL, mail);
                registro.put(COLUMN_USUARIO_CONTRASENIA, contrasenia);
                db.insert(TABLE_USUARIO, null, registro);

                db.close();
                return true;
            }
        }
        public Boolean insertSucursales(String nombre, String direccion, String telefono) {

            if(sucursalRegistrada(nombre)){
                return false;
            }else{
                SQLiteDatabase db = getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(COLUMN_SUCURSAL_NOMBRE, nombre);
                registro.put(COLUMN_SUCURSAL_DIRECCION, direccion);
                registro.put(COLUMN_SUCURSAL_TELEFONO, telefono);

                db.insert(TABLE_SUCURSAL, null, registro);

                db.close();
                return true;
            }
        }
        public Boolean insertProductos(String nombre, String descripcion, String precio) {

            if(productoregistrado(nombre)){
                return false;
            }else{
                SQLiteDatabase db = getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(COLUMN_PRODUCTO_NOMBRE, nombre);
                registro.put(COLUMN_PRODUCTO_DESCRIPCION, descripcion);
                registro.put(COLUMN_PRODUCTO_PRECIO, precio);

                db.insert(TABLE_PRODUCTO, null, registro);

                db.close();
                return true;
            }
        }
        /*---------------------------------------------------------------------------------------*/
        /*READ*/
        public Map<String,String> consulta(int id){
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE id = '" + id + "' " , null);
            if(cursor.moveToFirst()){
                Map<String, String> consulta = new LinkedHashMap<String, String>();

                consulta.put("nombre", cursor.getString(1));
                consulta.put("apellido",  cursor.getString(2));
                consulta.put("mail",  cursor.getString(3));
                consulta.put("contrasenia",  cursor.getString(4));
                return consulta;
            }else{
                return null;

            }

        }
        public Map<String,String> consultaproductos(int id){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM producto WHERE id = '" + id + "' " , null);            if(cursor.moveToFirst()){
                Map<String, String> consulta = new LinkedHashMap<String, String>();

                consulta.put("nombre", cursor.getString(1));
                consulta.put("descripcion",  cursor.getString(2));
                consulta.put("precio",  cursor.getString(3));

                return consulta;
            }else{
                return null;
            }

        }
        public Map<String,String> consultasucursales(int id){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM sucursales WHERE id = '" + id + "' " , null);            if(cursor.moveToFirst()){
                Map<String, String> consulta = new LinkedHashMap<String, String>();

                consulta.put("nombre", cursor.getString(1));
                consulta.put("direccion",  cursor.getString(2));
                consulta.put("telefono",  cursor.getString(3));

                return consulta;
            }else{
                return null;
            }

        }
        /*---------------------------------------------------------------------------------------*/
        /*UPDATE*/
        public String queryEditarUsuario(int id, String nombre, String apellido, String mail, String contrasenia){
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE usuario SET");
            sb.append(" nombre = ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append("apellido = ");
            sb.append(" '");
            sb.append(apellido);
            sb.append("' ");
            sb.append(" , ");
            sb.append("mail = ");
            sb.append(" '");
            sb.append(mail);
            sb.append("' ");
            sb.append(" , ");
            sb.append("contrasenia = ");
            sb.append(" '");
            sb.append(contrasenia);
            sb.append("' ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        public String queryEditarProducto(int id, String nombre, String descripcion, String precio){
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE producto SET");
            sb.append(" nombre = ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append("descripcion = ");
            sb.append(" '");
            sb.append(descripcion);
            sb.append("' ");
            sb.append(" , ");
            sb.append("precio = ");
            sb.append(" '");
            sb.append(precio);
            sb.append("' ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        public String queryEditarSucursal(int id, String nombre, String direccion, String telefono){
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE producto SET");
            sb.append(" nombre = ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append("direccion = ");
            sb.append(" '");
            sb.append(direccion);
            sb.append("' ");
            sb.append(" , ");
            sb.append("telefono = ");
            sb.append(" '");
            sb.append(telefono);
            sb.append("' ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        /*---------------------------------------------------------------------------------------*/
        /*DELETE*/
        public String queryEliminarUsuario(int id){
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM usuario ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        public String queryEliminarProducto(int id){
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM producto ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        public String queryEliminarSucursal(int id){
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM sucursales ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }
        /*---------------------------------------------------------------------------------------*/
        //METODOS LISTAR
        public ArrayList<Usuarios> mostrarUsuarios(){
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Usuarios>listaUsuarios = new ArrayList<>();
            Usuarios usu=null;
            Cursor cursor = db.rawQuery("SELECT * FROM usuario" , null);

            if(cursor.moveToFirst()){
                do {
                    usu= new Usuarios();
                    usu.setId(cursor.getInt(0));
                    usu.setNombre(cursor.getString(1));
                    usu.setApellido(cursor.getString(2));
                    usu.setMail(cursor.getString(3));
                    usu.setContraseña(cursor.getString(4));
                    listaUsuarios.add(usu);
                }while (cursor.moveToNext());
            }
                cursor.close();
                return listaUsuarios;
        }
        public ArrayList<Productos> mostrarProductos(){
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Productos>listaProductos = new ArrayList<>();
            Productos prod=null;
            Cursor cursor = db.rawQuery("SELECT * FROM producto" , null);

            if(cursor.moveToFirst()){
                do {
                    prod= new Productos();
                    prod.setId(cursor.getInt(0));
                    prod.setNombre(cursor.getString(1));
                    prod.setDescripcion(cursor.getString(2));
                    prod.setPrecio(cursor.getString(3));


                    listaProductos.add(prod);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return listaProductos;
        }
        public ArrayList<Sucursales> mostrarSucursales() {
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Sucursales> listaSucursales = new ArrayList<>();
            Cursor cursor = db.rawQuery("SELECT * FROM sucursales",null);

            if (cursor.moveToFirst()) {
                do {
                    Sucursales sucursal = new Sucursales();
                    sucursal.setIdsucursales(cursor.getInt(0));
                    sucursal.setNombre(cursor.getString(1));
                    sucursal.setDireccion(cursor.getString(2));
                    sucursal.setTelefono(cursor.getString(3));
                    listaSucursales.add(sucursal);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return listaSucursales;
        }
        /*---------------------------------------------------------------------------------------*/
        //VALIDACION
        public Boolean usuarioRegistrado(String mail){
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE mail = '" + mail + "' " , null);
            if(cursor.getCount()>0){
                return true;
            }else{
                return false;
            }
        }
        public Boolean productoregistrado(String nombre){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM producto WHERE nombre = '" + nombre + "' " , null);

            if(cursor.getCount()>0){
                return true;
            }else{
                return false;
            }

        }
        public Boolean sucursalRegistrada(String nombre){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM sucursales WHERE nombre = '" + nombre + "' " , null);

            if(cursor.getCount()>0){
                return true;
            }else{
                return false;
            }
        }
        /*---------------------------------------------------------------------------------------*/
        /*METODOS PRECARGAR DATOS*/
        public String precargados(String nombre, String apellido, String mail, String contrasenia) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT OR IGNORE INTO usuario");
            sb.append("(");
            sb.append("nombre");
            sb.append(" , ");
            sb.append("apellido");
            sb.append(" , ");
            sb.append("mail");
            sb.append(" , ");
            sb.append("contrasenia");
            sb.append(")");
            sb.append(" VALUES ");
            sb.append("( ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(apellido);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(mail);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(contrasenia);
            sb.append("' ");
            sb.append(")");
            return sb.toString();
        }
        public String precargadossucursales(String nombre, String direccion, String telefono) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT OR IGNORE INTO sucursales");
            sb.append("(");
            sb.append("nombre");
            sb.append(" , ");
            sb.append("direccion");
            sb.append(" , ");
            sb.append("telefono");
            sb.append(")");
            sb.append(" VALUES ");
            sb.append("( ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(direccion);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(telefono);
            sb.append("' ");
            sb.append(")");
            return sb.toString();
        }
        public String productosprecargados(String nombre, String descripcion, String precio) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT OR IGNORE INTO producto");
            sb.append("(");
            sb.append("nombre");
            sb.append(" , ");
            sb.append("descripcion");
            sb.append(" , ");
            sb.append("precio");
            sb.append(")");
            sb.append(" VALUES ");
            sb.append("( ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(descripcion);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(precio);
            sb.append("' ");
            sb.append(")");
            return sb.toString();
        }
    }
