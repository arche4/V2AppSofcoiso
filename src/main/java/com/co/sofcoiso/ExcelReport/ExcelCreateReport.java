/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.ExcelReport;

import com.co.sofcoiso.report.ReportCitas;
import com.co.sofcoiso.report.ReportFormacion;
import com.co.sofcoiso.report.ReportPersona;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author manue
 */
public class ExcelCreateReport {

    private HSSFWorkbook archivoExcel = null;
    private HSSFSheet hojaArchivo = null;
    private String nombreHojaArchivo = "";
    private String nombreArchivoExcel = "";
    private List<String> encabezado = new ArrayList<String>();

    public ExcelCreateReport(final String hoja, final String nombreArchivo) {
        this.nombreHojaArchivo = hoja;
        this.nombreArchivoExcel = nombreArchivo;

    }

    public List<String> getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(List<String> encabezado) {
        this.encabezado = encabezado;
    }

    public boolean createPersona(List<ReportPersona> registrosReporte) {

        this.encabezado.add("cedula");
        this.encabezado.add("nombre");
        this.encabezado.add("genero");
        this.encabezado.add("fecha_nacimiento");
        this.encabezado.add("edad");
        this.encabezado.add("empresa");
        this.encabezado.add("antiguedad_empresa");
        this.encabezado.add("cargo");
        this.encabezado.add("fecha_clinica");
        this.encabezado.add("telefono");
        this.encabezado.add("Eps");
        this.encabezado.add("Afp");
        this.encabezado.add("Arl");
        this.encabezado.add("Direccion");
        this.encabezado.add("Comuna");
        this.encabezado.add("Recomendado");

        boolean response = false;
        this.archivoExcel = new HSSFWorkbook();
        this.hojaArchivo = this.archivoExcel.createSheet();
        this.archivoExcel.setSheetName(0, this.nombreHojaArchivo);
        CellStyle estiloEncabezadoCeldas = archivoExcel.createCellStyle();
        //Estilo Encabezado
        Font tipoFuente = archivoExcel.createFont();
        tipoFuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        estiloEncabezadoCeldas.setFont(tipoFuente);
        //Estilo Informacion reporte
        CellStyle estiloInfo = archivoExcel.createCellStyle();
        estiloInfo.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        estiloInfo.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Creo fila del reporte 
        HSSFRow filaEncabezadoArchivo = hojaArchivo.createRow(0);
        int numCelEncabezado = 0;
        for (String nomCellEncabe : this.encabezado) {
            HSSFCell cellEncabezado = filaEncabezadoArchivo.createCell(numCelEncabezado);
            cellEncabezado.setCellStyle(estiloEncabezadoCeldas);
            cellEncabezado.setCellValue(nomCellEncabe);
            numCelEncabezado++;
        }
        int numFile = 0;
        for (ReportPersona datoCell : registrosReporte) {
            //Crea fila o row para celdas 
            HSSFRow filaInfoTabla = hojaArchivo.createRow(numFile + 1);
            filaInfoTabla.createCell(0).setCellValue(datoCell.getCedula());
            filaInfoTabla.createCell(1).setCellValue(datoCell.getNombre());
            filaInfoTabla.createCell(2).setCellValue(datoCell.getGenero());
            filaInfoTabla.createCell(3).setCellValue(datoCell.getFechaNacimiento());
            filaInfoTabla.createCell(4).setCellValue(datoCell.getEdad());
            filaInfoTabla.createCell(5).setCellValue(datoCell.getEmpresa());
            filaInfoTabla.createCell(6).setCellValue(datoCell.getAntiguedadEmpresa());
            filaInfoTabla.createCell(7).setCellValue(datoCell.getCargo());
            filaInfoTabla.createCell(8).setCellValue(datoCell.getFechaClinica());
            filaInfoTabla.createCell(9).setCellValue(datoCell.getTelefono());
            filaInfoTabla.createCell(10).setCellValue(datoCell.getEps());
            filaInfoTabla.createCell(11).setCellValue(datoCell.getAfp());
            filaInfoTabla.createCell(12).setCellValue(datoCell.getArl());
            filaInfoTabla.createCell(13).setCellValue(datoCell.getDireccion());
            filaInfoTabla.createCell(14).setCellValue(datoCell.getComuna());
            filaInfoTabla.createCell(15).setCellValue(datoCell.getRecomendado());
            numFile++;
        }
        //Genero el archivo
        try {
            FileOutputStream file = new FileOutputStream(this.nombreArchivoExcel);
            this.archivoExcel.write(file);
            file.close();
        } catch (Exception e) {
            response = false;
        }

        return response;
    }
    
       public boolean createFormacion(List<ReportFormacion> registrosReporte) {

        this.encabezado.add("id_formacion");
        this.encabezado.add("tipo_formacion");
        this.encabezado.add("fecha_formacion");
        this.encabezado.add("temas");
        this.encabezado.add("numeroAsistentes");

        boolean response = false;
        this.archivoExcel = new HSSFWorkbook();
        this.hojaArchivo = this.archivoExcel.createSheet();
        this.archivoExcel.setSheetName(0, this.nombreHojaArchivo);
        CellStyle estiloEncabezadoCeldas = archivoExcel.createCellStyle();
        //Estilo Encabezado
        Font tipoFuente = archivoExcel.createFont();
        tipoFuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        estiloEncabezadoCeldas.setFont(tipoFuente);
        //Estilo Informacion reporte
        CellStyle estiloInfo = archivoExcel.createCellStyle();
        estiloInfo.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        estiloInfo.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Creo fila del reporte 
        HSSFRow filaEncabezadoArchivo = hojaArchivo.createRow(0);
        int numCelEncabezado = 0;
        for (String nomCellEncabe : this.encabezado) {
            HSSFCell cellEncabezado = filaEncabezadoArchivo.createCell(numCelEncabezado);
            cellEncabezado.setCellStyle(estiloEncabezadoCeldas);
            cellEncabezado.setCellValue(nomCellEncabe);
            numCelEncabezado++;
        }
        int numFile = 0;
        for (ReportFormacion datoCell : registrosReporte) {
            //Crea fila o row para celdas 
            HSSFRow filaInfoTabla = hojaArchivo.createRow(numFile + 1);
            filaInfoTabla.createCell(0).setCellValue(datoCell.getId_formacion());
            filaInfoTabla.createCell(1).setCellValue(datoCell.getTipo_formacion());
            filaInfoTabla.createCell(2).setCellValue(datoCell.getFecha_formacion());
            filaInfoTabla.createCell(3).setCellValue(datoCell.getTemas());
            filaInfoTabla.createCell(4).setCellValue(datoCell.getNumeroAsistentes());
            numFile++;
        }
        //Genero el archivo
        try {
            FileOutputStream file = new FileOutputStream(this.nombreArchivoExcel);
            this.archivoExcel.write(file);
            file.close();
        } catch (Exception e) {
            response = false;
        }

        return response;
    }
       
      public boolean createCita(List<ReportCitas> registrosReporte) {

        this.encabezado.add("Cedula");
        this.encabezado.add("Persona");
        this.encabezado.add("Fecha");
        this.encabezado.add("Estado");

        boolean response = false;
        this.archivoExcel = new HSSFWorkbook();
        this.hojaArchivo = this.archivoExcel.createSheet();
        this.archivoExcel.setSheetName(0, this.nombreHojaArchivo);
        CellStyle estiloEncabezadoCeldas = archivoExcel.createCellStyle();
        //Estilo Encabezado
        Font tipoFuente = archivoExcel.createFont();
        tipoFuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        estiloEncabezadoCeldas.setFont(tipoFuente);
        //Estilo Informacion reporte
        CellStyle estiloInfo = archivoExcel.createCellStyle();
        estiloInfo.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        estiloInfo.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Creo fila del reporte 
        HSSFRow filaEncabezadoArchivo = hojaArchivo.createRow(0);
        int numCelEncabezado = 0;
        for (String nomCellEncabe : this.encabezado) {
            HSSFCell cellEncabezado = filaEncabezadoArchivo.createCell(numCelEncabezado);
            cellEncabezado.setCellStyle(estiloEncabezadoCeldas);
            cellEncabezado.setCellValue(nomCellEncabe);
            numCelEncabezado++;
        }
        int numFile = 0;
        for (ReportCitas datoCell : registrosReporte) {
            //Crea fila o row para celdas 
            HSSFRow filaInfoTabla = hojaArchivo.createRow(numFile + 1);
            filaInfoTabla.createCell(0).setCellValue(datoCell.getCedula());
            filaInfoTabla.createCell(1).setCellValue(datoCell.getPersona());
            filaInfoTabla.createCell(2).setCellValue(datoCell.getFecha());
            filaInfoTabla.createCell(3).setCellValue(datoCell.getEstado());
            numFile++;
        }
        //Genero el archivo
        try {
            FileOutputStream file = new FileOutputStream(this.nombreArchivoExcel);
            this.archivoExcel.write(file);
            file.close();
        } catch (Exception e) {
            response = false;
        }

        return response;
    }

}
