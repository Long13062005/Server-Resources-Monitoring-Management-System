import React, { useState, useEffect } from "react";
import Chart from "react-apexcharts";

export default function Dashboard() {
    const [metrics, setMetrics] = useState({
        cpu: 10,
        ram: 20,
        disk: 30,
        network: 5,
    });

    // Mô phỏng fetch API từ Spring Boot backend
    useEffect(() => {
        const interval = setInterval(() => {
            // Replace with real API fetch here
            setMetrics({
                cpu: Math.floor(Math.random() * 100),
                ram: Math.floor(Math.random() * 100),
                disk: Math.floor(Math.random() * 100),
                network: Math.floor(Math.random() * 50),
            });
        }, 2000);
        return () => clearInterval(interval);
    }, []);

    const chartOptions = {
        chart: {
            type: "radialBar",
            animations: {
                enabled: true,
                speed: 800,
            },
        },
        plotOptions: {
            radialBar: {
                dataLabels: {
                    name: { fontSize: "18px" },
                    value: { fontSize: "24px" },
                },
            },
        },
        labels: ["CPU", "RAM", "Disk", "Network"],
        colors: ["#4f46e5", "#059669", "#f59e0b", "#10b981"],
    };

    const chartSeries = [metrics.cpu, metrics.ram, metrics.disk, metrics.network];

    return (
        <div className="min-h-screen bg-gray-950 text-white px-6 py-10">
            <h1 className="text-3xl font-bold text-center mb-8">📊 Server Monitoring Dashboard</h1>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-8 max-w-5xl mx-auto">
                <div className="bg-gray-900 p-6 rounded-xl shadow-md border border-gray-800">
                    <Chart options={chartOptions} series={chartSeries} type="radialBar" height={350} />
                </div>

                <div className="bg-gray-900 p-6 rounded-xl shadow-md border border-gray-800 space-y-4">
                    <Metric label="CPU Usage" value={metrics.cpu} color="text-indigo-500" />
                    <Metric label="RAM Usage" value={metrics.ram} color="text-green-500" />
                    <Metric label="Disk Usage" value={metrics.disk} color="text-yellow-500" />
                    <Metric label="Network Load" value={metrics.network} color="text-emerald-400" />
                </div>
            </div>
        </div>
    );
}

function Metric({ label, value, color }) {
    return (
        <div className="flex justify-between text-sm">
            <span className="text-gray-400">{label}</span>
            <span className={`font-bold ${color}`}>{value}%</span>
        </div>
    );
}
