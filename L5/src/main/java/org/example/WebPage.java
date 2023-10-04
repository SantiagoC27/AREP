package org.example;

public class WebPage {
    public String getIndex(){
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<style>\n" +
                "    form {\n" +
                "        margin-bottom: 20px;\n" +
                "    }\n" +
                "\t</style>" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <!-- Form para el Seno -->\n" +
                "    <form id=\"sinForm\">\n" +
                "        <label for=\"sinValor\">Calcular Seno:</label>\n" +
                "        <input type=\"text\" id=\"sinValor\" name=\"sinValor\" required>\n" +
                "        <button type=\"button\" onclick=\"calcularSeno()\">Enviar</button>\n" +
                "        <div id=\"resultadoSeno\"></div>\n" +
                "    </form>\n" +
                "\n" +
                "    <!-- Form para el Coseno -->\n" +
                "    <form id=\"cosForm\">\n" +
                "        <label for=\"cosValor\">Calcular Coseno:</label>\n" +
                "        <input type=\"text\" id=\"cosValor\" name=\"cosValor\" required>\n" +
                "        <button type=\"button\" onclick=\"calcularCoseno()\">Enviar</button>\n" +
                "        <div id=\"resultadoCoseno\"></div>\n" +
                "    </form>\n" +
                "\n" +
                "    <!-- Form para Palíndromo -->\n" +
                "    <form id=\"palindromeForm\">\n" +
                "        <label for=\"palindromeValor\">Verificar Palíndromo:</label>\n" +
                "        <input type=\"text\" id=\"palindromeValor\" name=\"palindromeValor\" required>\n" +
                "        <button type=\"button\" onclick=\"verificarPalindromo()\">Enviar</button>\n" +
                "        <div id=\"resultadoPalindromo\"></div>\n" +
                "    </form>\n" +
                "\n" +
                "    <!-- Form para la Raíz Cuadrada de la Suma de Cuadrados -->\n" +
                "    <form id=\"sqrtForm\">\n" +
                "        <label for=\"sqrtValor1\">Calcular la magnitud de un vector, Primer valor:</label>\n" +
                "        <input type=\"text\" id=\"sqrtValor1\" name=\"sqrtValor1\" required>\n" +
                "        <label for=\"sqrtValor2\">Segundo valor:</label>\n" +
                "        <input type=\"text\" id=\"sqrtValor2\" name=\"sqrtValor2\" required>\n" +
                "        <button type=\"button\" onclick=\"magnitudVector()\">Enviar</button>\n" +
                "        <div id=\"resultSqrt\"></div>\n" +
                "    </form>\n" +
                "\n" +
                "    <script>\n" +
                "        function calcularSeno() {\n" +
                "            const valorIngresado = document.getElementById('sinValor').value;\n" +
                "            const result = document.getElementById('resultadoSeno');\n" +
                "\n" +
                "            fetch(`/sin?value=${valorIngresado}`)\n" +
                "                .then(response => response.text())\n" +
                "                .then(data => {\n" +
                "                    result.textContent = `El seno de ${valorIngresado} es: ${data}`;\n" +
                "                })\n" +
                "        }\n" +
                "\n" +
                "        function calcularCoseno() {\n" +
                "            const valorIngresado = document.getElementById('cosValor').value;\n" +
                "            const result = document.getElementById('resultadoCoseno');\n" +
                "\n" +
                "            fetch(`/cos?value=${valorIngresado}`)\n" +
                "                .then(response => response.text())\n" +
                "                .then(data => {\n" +
                "                    result.textContent = `El coseno de ${valorIngresado} es: ${data}`;\n" +
                "                })\n" +
                "        }\n" +
                "\n" +
                "        function verificarPalindromo() {\n" +
                "            const cadena = document.getElementById('palindromeValor').value;\n" +
                "            const result = document.getElementById('resultadoPalindromo');\n" +
                "\n" +
                "            fetch(`/ispalindrome?value=${cadena}`)\n" +
                "                .then(response => response.text())\n" +
                "                .then(data => {\n" +
                "                    result.textContent = `La palabra ${cadena} ${data}`;\n" +
                "                })\n" +
                "        }\n" +
                "\n" +
                "        function magnitudVector() {\n" +
                "            const valor1 = document.getElementById('sqrtValor1').value;\n" +
                "            const valor2 = document.getElementById('sqrtValor2').value;\n" +
                "            const result = document.getElementById('resultSqrt');\n" +
                "\n" +
                "            fetch(`/vector?value1=${valor1}&value2=${valor2}`)\n" +
                "                .then(response => response.text())\n" +
                "                .then(data => {\n" +
                "                    result.textContent = `La magnitud dvector real de dos dimensiones: ${data}`;\n" +
                "                })\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
