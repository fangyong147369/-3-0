//package com.zc.sys.common.util.validate;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.stream.XMLEventReader;
//import javax.xml.stream.XMLEventWriter;
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLOutputFactory;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//
//import de.odysseus.staxon.json.JsonXMLConfig;
//import de.odysseus.staxon.json.JsonXMLConfigBuilder;
//import de.odysseus.staxon.json.JsonXMLInputFactory;
//import de.odysseus.staxon.json.JsonXMLOutputFactory;
//
//public class XmlTool {
//
//	DocumentBuilderFactory factory;
//	DocumentBuilder docBuilder;
//	Document doc;
//	public NodeList nodeList;
//
//	public XmlTool() {
//	}
//
//	public void SetDocument(String xml) {
//
//		try {
//			factory = DocumentBuilderFactory.newInstance();
//			factory.setValidating(false);
//			docBuilder = factory.newDocumentBuilder();
//			xml = xml.trim();
//
//			InputStream inputStream = new ByteArrayInputStream(
//					xml.getBytes("utf-8"));// xml为要解析的字符串
//			docBuilder = factory.newDocumentBuilder();
//			doc = docBuilder.parse(inputStream);
//
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//		}
//
//	}
//
//	public int getNodeListCount(String NodeName) {
//		NodeList list = doc.getDocumentElement().getElementsByTagName(NodeName);
//		int count = list.getLength();
//		return count;
//	}
//
//	public String getNodeValue(String NodeName) {
//
//		try {
//			NodeList list = doc.getDocumentElement().getElementsByTagName(
//					NodeName);
//			if (list == null || list.getLength() <= 0) {
//				return "";
//			}
//
//			String txt = list.item(0).getFirstChild().getNodeValue() == null ? ""
//					: list.item(0).getFirstChild().getNodeValue();
//
//			return txt;
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//			return "";
//		}
//
//	}
//
//	public String getNodeXml(String... NodeNames) {
//		java.lang.StringBuilder sb = new java.lang.StringBuilder();
//
//		try {
//			NodeList list = doc.getDocumentElement().getChildNodes();
//			System.out.println(list.getLength());
//
//			if (list.getLength() > 0) {
//				for (int i = 0; i < list.getLength(); i++) {
//					if (!RemoveChild(list.item(i).getNodeName(), NodeNames)) {
//						String txt = getNodeValue(list.item(i).getNodeName());
//						if (txt == "")
//							sb.append("<" + list.item(i).getNodeName() + "></"
//									+ list.item(i).getNodeName() + ">");
//						else
//							sb.append("<" + list.item(i).getNodeName() + ">"
//									+ txt + "</" + list.item(i).getNodeName()
//									+ ">");
//
//						System.out.println(sb.toString().trim());
//					}
//				}
//
//			}
//			String txt = sb.toString();
//
//			return txt;
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//			return "";
//		}
//
//	}
//
//	private boolean RemoveChild(String NodeName, String... NodeNames) {
//		for (int i = 0; i < NodeNames.length; i++) {
//			if (NodeName.equals(NodeNames[i]))
//				return true;
//			else
//				return false;
//		}
//		return false;
//	}
//
//	public NodeList getNodeList(String NodeName) {
//		NodeList list = doc.getDocumentElement().getElementsByTagName(NodeName);
//		return list;
//	}
//
//	/**
//	 * 解析xml使用
//	 *
//	 * @param xml
//	 * @param nodeName
//	 * @return
//	 */
//	public static String getXmlNodeValue(String xml, String nodeName) {
//		String temp1[] = xml.split("<" + nodeName + ">");
//		String temp2[] = temp1[1].split("</" + nodeName + ">");
//		String resultXml = temp2[0];
//		return resultXml;
//	}
//
//	/**
//	 * 解析xml使用
//	 * 保留节点
//	 * @param xml
//	 * @param nodeName
//	 * @return
//	 */
//	public static String getXmlNodeValueIncludeNode(String xml, String nodeName) {
//		String resultXml = getXmlNodeValue(xml, nodeName);
//		resultXml = "<" + nodeName + ">" + resultXml + "</" + nodeName + ">";
//		return resultXml;
//	}
//
//	public static String[] getArrayByIndex(String[][] ary, int index) {
//		String[] new_ary = new String[ary[index].length];
//		for (int i = 0; i < ary[index].length; i++) {
//			new_ary[i] = ary[index][i];
//		}
//		return new_ary;
//	}
//
//	public static String format2Str(double d) {
//		DecimalFormat df = new DecimalFormat("0.00");
//		String ds = df.format(d);
//		return ds;
//	}
//
//	/**
//	 * 格式化double数值 如截取小数点后的一位 0.01-》0.1 0.00-》0.0
//	 *
//	 * @param d
//	 *            被格式化的double值
//	 * @param scale
//	 *            截取的小数位数
//	 * @return
//	 */
//	public static double formatCeil2Str(double d, int scale) {
//		if (scale < 0) {
//			throw new IllegalArgumentException(
//					"The scale must be a positive integer or zero");
//		}
//		BigDecimal b = new BigDecimal(Double.toString(d));
//		BigDecimal one = new BigDecimal("1");
//		return b.divide(one, scale, BigDecimal.ROUND_CEILING).doubleValue();
//	}
//
//	/**
//	 * 去除数字的科学计数法
//	 *
//	 * @param d
//	 * @return
//	 */
//	public static String format(double d) {
//		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
//		nf.setGroupingUsed(false);
//		return nf.format(d);
//	}
//
//	/**
//	 * xml string convert to json string
//	 */
//	public static String xml2json(String xml) {
//		StringReader input = new StringReader(xml);
//		StringWriter output = new StringWriter();
//		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true)
//				.autoPrimitive(true).prettyPrint(false).build();
//		try {
//			XMLEventReader reader = XMLInputFactory.newInstance()
//					.createXMLEventReader(input);
//			XMLEventWriter writer = new JsonXMLOutputFactory(config)
//					.createXMLEventWriter(output);
//			writer.add(reader);
//			reader.close();
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				output.close();
//				input.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return output.toString();
//	}
//
//	/**
//	 * json string convert to xml string
//	 */
//	public static String jsonToxml(String json) {
//		StringReader input = new StringReader(json);
//		StringWriter output = new StringWriter();
//		JsonXMLConfig config = new JsonXMLConfigBuilder()
//				.virtualRoot("ap").multiplePI(false)
//				.repairingNamespaces(false).build();
//		try {
//			XMLEventReader reader = new JsonXMLInputFactory(config)
//					.createXMLEventReader(input);
//			XMLEventWriter writer = XMLOutputFactory.newInstance()
//					.createXMLEventWriter(output);
//			// writer = new PrettyXMLEventWriter(writer);//输出格式
//			writer.add(reader);
//			reader.close();
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				output.close();
//				input.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return output.toString();
//	}
//
//	public static void main(String[] args) {
//		System.out.println(formatCeil2Str(2.220000000001, 2));
//	}
//}
