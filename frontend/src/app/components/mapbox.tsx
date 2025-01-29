import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  useMapEvents,
} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import { useCenterContext } from "../context/center-context";
import { CenterContextProps } from "../interface/center-interface";
import { MakePolygons } from "./make-polygons";
import { MakeNodes } from "./make-nodes";
import { Button } from "@/components/ui/button";

// Fix for default marker icon issue
L.Icon.Default.prototype.options.iconUrl =
  "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png";
L.Icon.Default.prototype.options.iconRetinaUrl =
  "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png";
L.Icon.Default.prototype.options.shadowUrl =
  "https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png";
L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png",
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png",
  shadowUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png",
});

function LocationMarker({ setCenter }: CenterContextProps) {
  useMapEvents({
    moveend(e) {
      const map = e.target;
      setCenter({ x: map.getCenter().lat, y: map.getCenter().lng });
    },
  });
  return null;
}

const MapBox = () => {
  const { center, setCenter, data, setData } = useCenterContext();

  return (
    <div>
      <div className="w-[600px] h-[600px]">
        <MapContainer
          center={[center.x, center.y]}
          zoom={13}
          scrollWheelZoom={true}
          style={{ height: "100%", width: "100%" }}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[center.x, center.y]}>
            <Popup>
              You are here. <br />
            </Popup>
          </Marker>
          {data && <MakePolygons elements={data.elements} />}
          {data && <MakeNodes elements={data.elements} />}
          <LocationMarker
            center={center}
            setCenter={setCenter}
            data={data}
            setData={setData}
          />
        </MapContainer>
      </div>
      <Button
        className="m-1"
        onClick={() =>
          setCenter({ x: 54.39482637467512, y: 18.574318885803226 })
        }
      >
        Put possition on UG
      </Button>
    </div>
  );
};

export default MapBox;
