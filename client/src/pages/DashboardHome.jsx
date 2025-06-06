import React from "react";
import DashboardLayout from "../components/DashboardLayout";
import Dashboard from "./Dashboard";

export default function DashboardHome() {
    return (
        <DashboardLayout>
            <h1 className="text-3xl font-semibold mb-4">📊 Live Metrics</h1>
            <Dashboard />
        </DashboardLayout>
    );
}
