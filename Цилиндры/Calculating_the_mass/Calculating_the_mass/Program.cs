using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;



namespace Calculating_the_mass
{
    // Структура для хранения информации о материале
    struct Material
    {
        public string Name;
        public double Density; // плотность в г/см^3
    }

    class Program
    {
        static List<Material> materials = new List<Material>();

        static void Main(string[] args)
        {
            LoadMaterials("data.txt");
            int choice;
            do
            {
                choice = ShowMenu();
                if (choice > 0 && choice <= materials.Count)
                {
                    CalculateAndDisplayMass(materials[choice - 1]);
                }
                else if (choice != 0)
                {
                    Console.WriteLine("Некорректный выбор.");
                }
            } while (choice != 0);
            Console.WriteLine("Программа завершила работу!");
        }

        static void LoadMaterials(string filename)
        {
            try
            {
                //Console.WriteLine(Directory.GetCurrentDirectory() + "\\..\\..\\..\\data.txt");
                StreamReader sr = new StreamReader(Directory.GetCurrentDirectory() + "/../../../data.txt");
                string line;
                while ((line = sr.ReadLine()) != null)
                {
                    string[] parts = line.Split(' ');
                    if (parts.Length == 2)
                    {
                        Material material = new Material();
                        material.Name = parts[0];
                        material.Density = Double.Parse(parts[1]);
                        materials.Add(material);

                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Ошибка при загрузке материалов: {e.Message}");
            }
        }

        static int ShowMenu()
        {
            Console.WriteLine("Меню выбора материала:");
            for (int i = 0; i < materials.Count; i++)
            {
                Console.WriteLine($"{i + 1 , 2}. {materials[i].Name}");
            }
            Console.WriteLine(" 0. Завершить работу");
            Console.Write("Выберите материал: ");
            int choice = int.Parse(Console.ReadLine());
            return choice;
        }

        static void CalculateAndDisplayMass(Material material)
        {
            Console.Write("Введите диаметр стержня (мм): ");
            double diameter = double.Parse(Console.ReadLine());
            Console.Write("Введите длину стержня (мм): ");
            double length = double.Parse(Console.ReadLine());

            double volume = CalculateCylinderVolume(diameter, length) / 1000; // Получаем из мм^3 см^3 
            double mass = volume * material.Density; // см * г/см = г

            Console.WriteLine($"Характеристики стержня:");
            Console.WriteLine($"Материал: {material.Name}, Плотность: {material.Density} г/см^3");
            Console.WriteLine($"Объем: {volume:f4} см^3, Масса: {(mass > 1000 ? mass / 1000 : mass):f4} {(mass > 1000 ? "кг" : "г"):f4}");
            Console.WriteLine("\n");
        }

        static double CalculateCylinderVolume(double diameter, double length)
        {
            double radius = diameter / 2.0;
            return Math.PI * radius * radius * length;
        }
    }
}