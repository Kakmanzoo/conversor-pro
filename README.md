# 💱 Conversor de Monedas en Java

Un conversor de monedas interactivo y moderno desarrollado en **Java con Swing**, que permite convertir valores desde pesos colombianos (COP) a diferentes divisas internacionales. ¡Incluye interfaz gráfica, historial de conversiones y gráficos ilustrativos!

## 🚀 Características

- Conversión en tiempo real desde **COP a USD, EUR, PEN y JPY**
- Interfaz limpia, intuitiva y responsiva
- Muestra **banderas y gráficos** según la divisa seleccionada
- Historial visual de conversiones con fecha y hora
- Almacenamiento del historial en un archivo `.txt`
- Compatible con imágenes personalizadas para representar cada moneda

## 🖼️ Vista previa

| Conversión | Historial | Visual |
|------------|-----------|--------|
| ![ejemplo](img/vista1.png) | ![historial](img/vista2.png) | ![graficos](img/vista3.png) |

> *Nota: Las imágenes anteriores son ejemplos. Puedes personalizarlas agregando tus propias banderas y gráficos en la carpeta `img/`.*

## 🛠️ Tecnologías utilizadas

- 🧠 **Java** (versión 8+)
- 🎨 **Swing** para la interfaz gráfica
- 🖼️ **JLabel + ImageIcon** para mostrar imágenes
- 📄 **FileWriter** para guardar el historial

## 🗃️ Estructura del proyecto

ConversorDeMonedas/
│
├── img/
│ ├── us.png
│ ├── ue.png
│ ├── pe.png
│ ├── jp.png
│ ├── grafico_usd.png
│ ├── grafico_eur.png
│ ├── grafico_pen.png
│ ├── grafico_jpy.png
│ └── (opcional) default.png, grafico_default.png
│
├── Historial_conversiones.txt
└── Conversor.java


## 🎯 ¿Cómo usarlo?

1. Clona el repositorio o descarga los archivos.
2. Asegúrate de tener **Java** instalado.
3. Compila y ejecuta el archivo:


## 🎯 ¿Cómo usarlo?

1. Clona el repositorio o descarga los archivos.
2. Asegúrate de tener Java instalado.
3. Compila y ejecuta el archivo:

```bash
javac Conversor.java
java Conversor
```

4. Ingresa un valor en pesos colombianos (COP), selecciona una divisa y haz clic en **"Convertir"**.

¡Listo! Verás el resultado, la bandera, el gráfico y la entrada correspondiente en tu historial.

---

## 🛠️ Notas adicionales

Puedes actualizar las tasas de conversión directamente en el método `convertirMoneda()` si deseas reflejar nuevos valores.

Personaliza las imágenes dentro de la carpeta `img/` para adaptar el diseño a tu gusto.

---

## 👨‍💻 Autor

Desarrollado por Jesús Xamir Higuera Losada · Juan Sebastian Perez Angarita · Joher Camilo Vargas Mache  
🎓 Estudiantes de Ingeniería de Software  
💡 Apasionados por la programación, el diseño y los desafíos creativos.

---

## 📄 Licencia

Este proyecto está disponible bajo la licencia MIT.  
Puedes modificarlo, distribuirlo y utilizarlo libremente con fines educativos o personales.
